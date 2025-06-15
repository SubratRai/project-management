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

    //Get dashboard report by project ID
    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectDashboardResponse> getProjectDashboard(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getProjectDashboard(projectId));
    }
    @GetMapping("/admin")

    public String adminDashboard() {
        return "Welcome to Admin Dashboard";
    }

    @GetMapping("/manager")

    public String managerDashboard() {
        return "Welcome to Manager Dashboard";
    }

    @GetMapping("/developer")

    public String developerDashboard() {
        return "Welcome to Developer Dashboard";
    }
}
