package com.expense.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Size(max = 50, message = "Username must be at most 50 characters")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(max = 100, message = "Password must be at most 100 characters")
    @Column(name = "password")
    private String password;

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

	public User(Long id,
			@NotBlank(message = "Username cannot be blank") @Size(max = 50, message = "Username must be at most 50 characters") String username,
			@NotBlank(message = "Password cannot be blank") @Size(max = 100, message = "Password must be at most 100 characters") String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

    // getters and setters
}
