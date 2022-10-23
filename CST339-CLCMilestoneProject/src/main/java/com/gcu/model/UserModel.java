package com.gcu.model;

public class UserModel {
    
    /**
     * 
     * Private Member variables
     * 
     */
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phone;
    private String password;
    
    
    /**
     * full Constructor
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @param username
     * @param phone
     * @param password
     */
    public UserModel(String firstName, String lastName, String email, String username, String phone, String password)
    {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.phone = phone;
		this.password = password;
	}
    
    /**
     * minimal UserModel
     * 
     * @param username
     * @param password
     */
    public UserModel(String username, String password)
    {
    	this.username = username;
    	this.password = password;
    }
    
    /**
     *  empty constructor
     */
    public UserModel()
    {
    	
    }
    
    
	/**
     * 
     * getters and setters for member variables of user model
     * 
     * @return
     */
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
