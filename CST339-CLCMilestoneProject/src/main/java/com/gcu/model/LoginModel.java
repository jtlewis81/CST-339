/**
 *  Jamie Lewis
 *  CST-339
 *  10/23/22
 *  
 *  CLC Milestone Project
 *   
 *  Login Module - LoginModel
 * 
 */

package com.gcu.model;

public class LoginModel {

	/**
	 * The LoginModel consists of "username" and "password" String variables.
	 */
	
    private String username;    
    
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
