package com.expense.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "User name cannot be blank")
    @Size(min = 4, message = "user name must be at least 4 characters")
    @Column(name = "user_name")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @Size(max = 100, message = "Password must be at most 100 characters")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "Role cannot be blank")
    @Size(max = 100, message = "Role must be at most 20 characters")
    @Column(name = "role")
    private String role;
    
    // getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	// constructor
	public User(Long id,
			@NotBlank(message = "User name cannot be blank") @Size(min = 4, message = "user name must be at least 4 characters") String username,
			@NotBlank(message = "Password cannot be blank") @Size(max = 100, message = "Password must be at most 100 characters") String password,
			@NotBlank(message = "Role cannot be blank") @Size(max = 100, message = "Role must be at most 20 characters") String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public User() {
		super();
		
	}
	 // toString
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
    
   
    
    
    
   
}

