package com.projectmanagement.project_management.service;


import com.projectmanagement.project_management.entity.User;
import com.projectmanagement.project_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //create or update user
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //get user by Id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
    }

    //delete user
    public void deleteUser(Long id){
        userRepository.findById(id);
    }


}
