package com.gcu.model;

import javax.validation.constraints.NotNull;

public class LoginModel {

	/**
	 * The LoginModel consists of "username" and "password" String variables.
	 * 
	 * There are min and max lengths for each that must be validated.
	 */
	
    @NotNull(message="User name is a required field")
    private String username;    
    
    @NotNull(message="Password is a required field")
    private String password;
    
    public void setUsername(String user) {
        username = user;
    }
    
    public String getUsername() {
        return username;
    }
    public void setPassword(String pass) {
        password = pass;
    }
    
    public String getPassword() {
        return password;
    }
}
