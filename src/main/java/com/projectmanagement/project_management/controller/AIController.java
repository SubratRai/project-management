package com.projectmanagement.project_management.controller;

import com.projectmanagement.project_management.dto.UserStoryRequest;
import com.projectmanagement.project_management.dto.UserStoryResponse;
import com.projectmanagement.project_management.service.AIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/ai")
public class AIController {

   // @Autowired
    private final AIServices aiServices;

    public AIController(AIServices aiServices){
        this.aiServices = aiServices;
    }

    @PostMapping( "/generate-user-stories")

    public ResponseEntity<List<String>> generateUserStories(@RequestBody String description) {
        System.out.println("ai controller hit description=" + description);
        List<String> stories = List.of("As a user, I want to login.", "As an admin, I want to manage users.");


        return ResponseEntity.ok(stories);
    }


}


