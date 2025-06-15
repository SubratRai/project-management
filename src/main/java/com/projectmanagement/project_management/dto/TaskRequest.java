package com.projectmanagement.project_management.dto;

import com.projectmanagement.project_management.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String comment;
    private TaskStatus status;
    private LocalDate deadline;
    private Long projectId;
    private Long assignedToId;
}