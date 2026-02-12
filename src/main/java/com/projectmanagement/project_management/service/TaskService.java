package com.projectmanagement.project_management.service;

import com.projectmanagement.project_management.dto.ProjectDashboardResponse;
import com.projectmanagement.project_management.dto.TaskRequest;
import com.projectmanagement.project_management.entity.Project;
import com.projectmanagement.project_management.entity.Task;
import com.projectmanagement.project_management.entity.User;
import com.projectmanagement.project_management.enums.TaskStatus;
import com.projectmanagement.project_management.repository.ProjectRepository;
import com.projectmanagement.project_management.repository.TaskRepository;
import com.projectmanagement.project_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public Task createTask(TaskRequest taskRequest) {
        Project project = projectRepository.findById(taskRequest.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found with ID: " + taskRequest.getProjectId()));

        User assignedTo = userRepository.findById(taskRequest.getAssignedToId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + taskRequest.getAssignedToId()));

        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(taskRequest.getStatus())
                .deadline(taskRequest.getDeadline())
                .comment(taskRequest.getComment())
                .project(project)
                .assignedTo(assignedTo)
                .build();

        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + id));
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAssignedToId(userId);
    }

    public void updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));
        task.setStatus(status);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public ProjectDashboardResponse getProjectDashboard(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        if (tasks.isEmpty()) {
            throw new NoSuchElementException("No tasks found for this project");
        }

        int total = tasks.size();
        int overdue = 0;
        int completed = 0;

        Map<TaskStatus, Integer> statusCounts = new HashMap<>();
        for (Task task : tasks) {
            TaskStatus status = task.getStatus();
            statusCounts.put(status, statusCounts.getOrDefault(status, 0) + 1);

            if (status == TaskStatus.DONE) {
                completed++;
            }

            if (task.getDeadline() != null && task.getDeadline().isBefore(LocalDate.now())
                    && task.getStatus() != TaskStatus.DONE) {
                overdue++;
            }
        }

        double percent = (completed * 100.0) / total;

        return ProjectDashboardResponse.builder()
                .projectId(projectId)
                .totalTasks(total)
                .taskStatusCounts(statusCounts)
                .overdueTasks(overdue)
                .completionPercentage(Math.round(percent * 100.0) / 100.0)
                .build();
    }
}
