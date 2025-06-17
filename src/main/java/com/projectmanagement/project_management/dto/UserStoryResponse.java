package com.projectmanagement.project_management.dto;

import java.util.List;

public class UserStoryResponse {
    private List<String> userStories;

    // Getter & Setter
    public List<String> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<String> userStories) {
        this.userStories = userStories;
    }
}