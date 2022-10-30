package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginModel {
	
	// class attributes
	
	@NotNull(message="Username is a required field")
	@Size(min=3, max=32, message="Username must be between 3 and 32 characters")
	private String username;
	
	@NotNull(message="Password is a required field")
	@Size(min=8, max=32, message="Password must be between 8 and 32 characters")
	private String password;
	
	
	// getters and setters for class attributes
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
	
}
