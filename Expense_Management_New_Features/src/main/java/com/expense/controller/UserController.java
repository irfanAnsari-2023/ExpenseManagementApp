package com.expense.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.globalException.InvalidUserException;
import com.expense.globalException.ResourceNotFoundException;
import com.expense.globalException.UserAlreadyExistException;
import com.expense.globalException.UserNotFoundException;
import com.expense.model.User;
import com.expense.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Get current user by username
    @GetMapping("/current-user")
    public User getCurrentUser(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found.");
        }
        return user;
    }
    

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        return ResponseEntity.ok(user.get());
    }
    
    // Get all users
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable) {
        List<User> users = (List<User>) userService.getAllUsers(pageable);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found.");
        }
        return ResponseEntity.ok(users);
    }
    // Register new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new InvalidUserException("Username and password cannot be null.");
        }
        if (userService.getUserByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Username " + user.getUsername() + " already exists.");
        }
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }
}

