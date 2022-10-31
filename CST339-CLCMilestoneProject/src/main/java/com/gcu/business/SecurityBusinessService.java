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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.gcu.Cst339ClcMilestoneProjectApplication;
import com.gcu.model.UserModel;
import com.gcu.model.PostModel;


@Service
public class SecurityBusinessService
{
    public UserModel currentlyLoggedIn = null; 
    public List<PostModel> SamplePosts = new ArrayList<PostModel>();
    
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
    		    SamplePosts = setSamplePosts();
    			return true;
    		}
    	}
		
    	// if no username and password combination match
		return false;
	}	
	
	/**
	 * Set sample Posts for PostsController testing. 
	 * 
	 * @return
	 */
	public List<PostModel> setSamplePosts()
	{
	    return new ArrayList<PostModel>() {
	        {
	            add(new PostModel(currentlyLoggedIn.getUsername(), "This is a sample Post.", LocalDateTime.now()));
                add(new PostModel(currentlyLoggedIn.getUsername(), "Hello World.", LocalDateTime.now()));
                add(new PostModel(currentlyLoggedIn.getUsername(), "Dogecoin (DOGE) was founded by software engineers Billy Markus and Jackson Palmer, and was launched in 2013. Dogecoin was created to make digital currency more fun, friendly, and approachable.", LocalDateTime.now()));
                add(new PostModel(currentlyLoggedIn.getUsername(), "The Red Ribbon Army was once destroyed by Son Goku. Individuals who carry on its spirit have created the ultimate androids -- Gamma 1 and Gamma 2. However, these two androids call themselves superheroes and start attacking Piccolo and Gohan.", LocalDateTime.now()));
	        }
	    };
	}
}
