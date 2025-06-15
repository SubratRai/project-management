package com.projectmanagement.project_management.controller;


import com.projectmanagement.project_management.dto.TaskRequest;
import com.projectmanagement.project_management.entity.Task;
import com.projectmanagement.project_management.enums.TaskStatus;
import com.projectmanagement.project_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // Create Task
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTask(taskRequest);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    // Get Task by id
    @GetMapping("/{id}")
    public ResponseEntity <Task> getTaskById(@PathVariable Long id){
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    //Get task by project id
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTaskByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }

    //get task by developer /user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId){
            return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }
    //update task status
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status ) {
        taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok("Task status updated to " + status);
    }

    //delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("task deleted successfully");
    }
}
