package com.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expense.model.User;
import com.expense.repository.UserRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    // method for getting user by username
    public User getUserByUsername(String username) {
        // Get the user by username from the UserRepository
        return userRepository.findByUsername(username);
    }

    
    // method for saving the user
    public void createUser(User user) {
    	// Create the user in the UserRepository
        userRepository.save(user);
    }

    // method for getting user by id
	public Optional<User> getUserById(@NotNull Long userId) {
		return userRepository.findById(userId);
		
	}

	// method for getting all the users
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
		
	}
    
}