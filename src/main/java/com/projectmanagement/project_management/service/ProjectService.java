package com.projectmanagement.project_management.service;


import com.projectmanagement.project_management.entity.Project;
import com.projectmanagement.project_management.entity.User;
import com.projectmanagement.project_management.enums.Role;
import com.projectmanagement.project_management.repository.ProjectRepository;
import com.projectmanagement.project_management.repository.UserRepository;
import com.projectmanagement.project_management.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;


import static com.projectmanagement.project_management.security.SecurityUtil.getLoggedInUserEmail;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    //create or update product
    public Project saveProject(Project project){
        return projectRepository.save(project);

    }

    //find project by id
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Project not found with ID: " + id));
    }

    //get all project
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    public List<Project> getProjectsForCurrentUser() {
        String email = SecurityUtil.getLoggedInUserEmail(); // Logged-in user's email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // If ADMIN return all projects
        if (user.getRole().equals(Role.ADMIN)) {
            return projectRepository.findAll();
        }

        //If MANAGER return only his projects
        if (user.getRole().equals(Role.MANAGER)) {
            return projectRepository.findByManager(user);
        }

        // Others not allowed
        throw new AccessDeniedException("Only Admin or Manager can view projects");
    }
    //delete project
    public void deletedProject(Long id){
        projectRepository.deleteById(id);
    }
}
