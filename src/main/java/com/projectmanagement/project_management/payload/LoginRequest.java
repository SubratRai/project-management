package com.projectmanagement.project_management.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
