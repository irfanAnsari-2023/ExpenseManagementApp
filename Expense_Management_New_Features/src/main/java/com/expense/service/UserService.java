package com.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.model.User;
import com.expense.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        // Get the user by username from the UserRepository
        return userRepository.findByUsername(username);
    }

    
    public void createUser(User user) {
    	// Create the user in the UserRepository
        userRepository.save(user);
    }
    
}