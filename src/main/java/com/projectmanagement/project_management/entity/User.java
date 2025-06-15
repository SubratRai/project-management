package com.projectmanagement.project_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectmanagement.project_management.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // this is a jpa entity it mapped used to mapped to table in db
@Table(name="users")//table name in db
@Data //lombok: generates getter setter tostring
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto generate id in db
    private Long id; //primary key

    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = true) // make email unique
    @Email(message = "invalid email format")
    @NotBlank(message = "Email is required")
    private String email;


    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

    @Enumerated(EnumType.STRING)// save enum as String("Admin") in db
    private Role role;
}
