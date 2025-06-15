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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private final TaskRepository taskRepository;

    //create or update task
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
    //Get Task by Id

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + id));
    }

    //get tasks by project id
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    // Get tasks by user id
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAssignedToId(userId);
    }

    //update task status
    public void updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));
        task.setStatus(status);
        taskRepository.save(task);
    }

    //delete task
    public void deleteTask(Long id){
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

        // Count by status
        Map<TaskStatus, Integer> statusCounts = new HashMap<>();
        for (Task task : tasks) {
            TaskStatus status = task.getStatus();
            statusCounts.put(status, statusCounts.getOrDefault(status, 0) + 1);

            if (status == TaskStatus.DONE) completed++;

            if (task.getDeadline() != null && task.getDeadline().isBefore(java.time.LocalDate.now())
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
                .completionPercentage(Math.round(percent * 100.0) / 100.0) // round to 2 decimal
                .build();
    }

}
