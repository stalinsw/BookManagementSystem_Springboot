package com.book.manage.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class User {

	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

	@Email
	@NotBlank(message = "Email is required")
	private String email;

	private String role = "USER";

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getEmail() {
		return email;
	}

public void setEmail(String email) {
	this.email = email;
}
}
