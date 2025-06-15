package com.projectmanagement.project_management.repository;

import com.projectmanagement.project_management.entity.Project;
import com.projectmanagement.project_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByManager(User manager);
}
