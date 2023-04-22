package com.expense.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expense.entity.User;
import com.expense.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    // method for getting all the users
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	// method for getting user by id
	public Optional<User> getUserById(@NotNull Long userId) {
		return userRepository.findById(userId);
		 
	}
	// method for saving the user
	public User saveUser(@Valid User user) {
        return userRepository.save(user);
    }
}

