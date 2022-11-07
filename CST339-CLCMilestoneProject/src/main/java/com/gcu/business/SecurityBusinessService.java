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
    private UserModel currentlyLoggedIn; 
    private List<UserModel> users; 
    
	/**
	 * verify that the submitted Username and Password match an existing user
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(List<UserModel> users, String username, String password)
	{
		this.setUsers(users);
		
		// check for valid user
    	for(UserModel user : users)
    	{
    		if (user.getUsername().equals(username) && user.getPassword().equals(password))
    		{    		
    			System.out.println("User successful login.");
    		    this.currentlyLoggedIn = user;     
    			return true;
    		}
    	}
		
    	// if no username and password combination match
		return false;
	}

	/**
	 * @return the currentlyLoggedIn
	 */
	public UserModel getCurrentlyLoggedIn() {
		return currentlyLoggedIn;
	}

	/**
	 * @param currentlyLoggedIn the currentlyLoggedIn to set
	 */
	public void setCurrentlyLoggedIn(UserModel currentlyLoggedIn) {
		this.currentlyLoggedIn = currentlyLoggedIn;
	}

	/**
	 * @return the users
	 */
	public List<UserModel> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
}
