package com.projectmanagement.project_management.dto;


import com.projectmanagement.project_management.enums.TaskStatus;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDashboardResponse {
    private Long projectId;
    private int totalTasks;
    private Map<TaskStatus, Integer> taskStatusCounts;
    private int overdueTasks;
    private double completionPercentage;
}
