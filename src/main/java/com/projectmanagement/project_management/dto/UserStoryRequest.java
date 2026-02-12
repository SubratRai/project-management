package com.projectmanagement.project_management.dto;

import jakarta.validation.constraints.NotBlank;

public class UserStoryRequest {

    @NotBlank(message = "Description is required")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
