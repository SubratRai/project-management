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
    public ResponseEntity<ProjectDashboardResponse> projectDashboard(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getProjectDashboard(projectId));
    }
    @GetMapping("/admin")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("welcome to admin Dashboard");
    }

    @GetMapping("/manager")
    public ResponseEntity<String> managerDashboard() {
        return ResponseEntity.ok("welcome to manager Dashboard");
    }

    @GetMapping("/developer")
    public ResponseEntity<String> developerDashboard() {
        return ResponseEntity.ok("welcome to developer Dashboard");
    }
}
