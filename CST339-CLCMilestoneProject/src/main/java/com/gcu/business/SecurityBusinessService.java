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

import org.springframework.stereotype.Service;
import com.gcu.Cst339ClcMilestoneProjectApplication;
import com.gcu.model.UserModel;


@Service
public class SecurityBusinessService
{
    public UserModel currentlyLoggedIn = null; 
    
	/**
	 * verify that the submitted username and password match an existing user
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(String username, String password)
	{
		// check for valid user
    	for(int i = 0; i < Cst339ClcMilestoneProjectApplication.Users.size(); i++)
    	{
    		// temp user data
    		UserModel user = Cst339ClcMilestoneProjectApplication.Users.get(i);
    		
    		// if the username and password match an existing user
    		if (user.getUsername().equals(username)
				&& user.getPassword().equals(password) )
    		{    			
    		    currentlyLoggedIn = user;     
    			return true;
    		}
    	}
		
    	// if no username and password combination match
		return false;
	}
}
