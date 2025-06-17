package com.projectmanagement.project_management.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AIServices {

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.api.base}")
    private String groqApiBase;

    private final WebClient webClient;

    public AIServices(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<String> generateUserStories(String description) {
        String prompt = "Generate 3 user stories based on this project description:\n" + description;

        Map<String, Object> body = Map.of(
                "model", "mixtral-8x7b-32768",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        Map response = this.webClient.post()
                .uri(groqApiBase+ "/chat/completions")
                .header("Authorization", "Bearer " + groqApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // ✅ Yaha content define ho raha hai
        String content = (String) ((Map)((Map)((List) response.get("choices")).get(0)).get("message")).get("content");

        // ❌ Doosri baar content define karne ki zarurat nahi
        // return content; <-- galat

        return Arrays.stream(content.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
    }
}

