package com.projectmanagement.project_management.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate deadline;

    //many project can be manage by one manager(user)
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    //many-to-many relation with users(developers in team)
    @ManyToMany
    @JoinTable(//here we created a joid table project_team with project_id and user_id
            name = "project_team",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User>teamMembers;
}
