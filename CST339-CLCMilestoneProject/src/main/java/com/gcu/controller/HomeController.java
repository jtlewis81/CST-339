package com.gcu.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.business.PostBusinessService;
import com.gcu.business.UserBusinessService;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

@Controller
@RequestMapping("/home")
public class HomeController 
{
	// VARIABLES 
    @Autowired
    private UserBusinessService userService;     
    @Autowired
    private PostBusinessService postService;
    
    // display home page 
    @GetMapping("/")
    public String display(Model model, Principal principal) 
    {   
    	UserEntity user = userService.getUserByUsername(principal.getName());
    	List<UserEntity> friends = userService.getAllFriends(principal.getName());
    	List<PostEntity> posts;
    	if (friends == null)
    	{
    		posts = postService.getAllPostsByUser(user);
    	}
    	else
    	{
    		posts = postService.getUserFeed(user, userService.getAllFriends(user.getUsername()));
    	}
    	
        model.addAttribute("title", "Home");
        model.addAttribute("pageName", "Home");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("userEntity", user);
        
        return "home";
    }
    
    @GetMapping("/find-friends")
    public String findFriends(Model model, Principal principal)
    {
    	UserEntity user = userService.getUserByUsername(principal.getName());
    	List<UserEntity> users = userService.getAllUsers();
    	List<UserEntity> friends = userService.getAllFriends(principal.getName());
    	
    	// REALLY need a better way to remove the current user and their friends from the list of users! This will not scale well!
    	for(int i = 0; i < users.size(); i++)
    	{
    		// remove self
    		if(users.get(i).getUsername().compareTo(user.getUsername()) == 0 )
    		{
    			users.remove(users.get(i));
    		}
    		
    	}
    	// check if friends is null and apply fix for null pointer if it is
    	if (friends == null)
    	{
    		friends = null;
    	}
    	else
    	{    		
    		// remove friends
    		for(int i = 0; i < friends.size(); i++)
	    	{
	    		for(int j = 0; j < users.size(); j++)
	    		{
	    			if(users.get(j).getUsername().compareTo(friends.get(i).getUsername()) == 0)
	    			{
	    				users.remove(users.get(j));
	    			}
	    		}
	    	}
    	}
    	
    	// possibly add sort method for alphabetical listing?
    	
    	model.addAttribute("title", "Find Friends");
    	model.addAttribute("pageName", "Find Friends");
    	model.addAttribute("username", principal.getName());	
    	model.addAttribute("users", users);
    	
    	return "find-friends";
    }
}
