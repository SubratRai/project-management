package com.projectmanagement.project_management.controller;

import com.projectmanagement.project_management.entity.Project;
import com.projectmanagement.project_management.repository.ProjectRepository;
import com.projectmanagement.project_management.service.ProjectService;
import com.projectmanagement.project_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    //Creating project

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project saved = projectService.saveProject(project);
        return ResponseEntity.ok(saved);

    }

    //getting all project
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    //getting project by id
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id){
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);

    }

    @GetMapping("/my-projects")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Project>> getProjectsForCurrentUser() {
        return ResponseEntity.ok(projectService.getProjectsForCurrentUser());
    }

    //deleting
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deletedProject(id);
        return  ResponseEntity.ok("Project deleted successfully");
    }
}
