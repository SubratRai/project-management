package com.projectmanagement.project_management.repository;

import com.projectmanagement.project_management.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedToId(Long userId);//developer ke tasks laane ke liye
    List<Task> findByProjectId(Long projectId);//used to fetch task project-wise
}
