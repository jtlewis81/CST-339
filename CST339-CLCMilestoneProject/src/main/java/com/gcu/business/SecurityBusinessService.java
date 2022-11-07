/**
 *  Jamie Lewis
 *  CST-339
 *  Last Updated: 10/30/22
 *  
 *  CLC Milestone Project
 *   
 *  Login Module - SecurityBusinessService
 * 
 */

package com.gcu.business;

import java.util.List;

import org.springframework.stereotype.Service;
import com.gcu.model.UserModel;


@Service
public class SecurityBusinessService
{
    public static UserModel currentlyLoggedIn; 
    public static List<UserModel> users; 
    
	/**
	 * verify that the submitted Username and Password match an existing user
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(List<UserModel> users, String username, String password)
	{
		this.users = users;
		
		// check for valid user
    	for(UserModel user : users)
    	{
    		if (user.getUsername().equals(username) && user.getPassword().equals(password))
    		{    		
    			System.out.println("User successful login.");
    		    currentlyLoggedIn = user;     
    			return true;
    		}
    	}
		
    	// if no username and password combination match
		return false;
	}
}
