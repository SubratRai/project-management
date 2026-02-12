package com.projectmanagement.project_management.controller;

import com.projectmanagement.project_management.dto.TaskRequest;
import com.projectmanagement.project_management.entity.Task;
import com.projectmanagement.project_management.enums.TaskStatus;
import com.projectmanagement.project_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTask(taskRequest);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTaskByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok("Task status updated to " + status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
