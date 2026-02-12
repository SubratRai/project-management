package com.projectmanagement.project_management.controller;

import com.projectmanagement.project_management.dto.ProjectDashboardResponse;
import com.projectmanagement.project_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final TaskService taskService;

    @GetMapping("/project/{projectId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProjectDashboardResponse> projectDashboard(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getProjectDashboard(projectId));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Welcome to admin dashboard");
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> managerDashboard() {
        return ResponseEntity.ok("Welcome to manager dashboard");
    }

    @GetMapping("/developer")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
    public ResponseEntity<String> developerDashboard() {
        return ResponseEntity.ok("Welcome to developer dashboard");
    }
}
