package com.projectmanagement.project_management.controller;

import com.projectmanagement.project_management.dto.UserStoryRequest;
import com.projectmanagement.project_management.dto.UserStoryResponse;
import com.projectmanagement.project_management.service.AIServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIServices aiServices;

    @PostMapping("/generate-user-stories")
    public ResponseEntity<UserStoryResponse> generateUserStories(@RequestBody UserStoryRequest request) {
        List<String> stories = aiServices.generateUserStories(request.getDescription());

        UserStoryResponse response = new UserStoryResponse();
        response.setUserStories(stories);
        return ResponseEntity.ok(response);
    }
}
