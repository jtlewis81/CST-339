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
        model.addAttribute("title", "Home");
        model.addAttribute("pageName", "Home");
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("posts", postService.getAllPostsByUser(userService.getUserByUsername(principal.getName())));
        
        return "home";
    }
    
    @GetMapping("/find-friends")
    public String findFriends(Model model, Principal principal)
    {
    	UserEntity user = userService.getUserByUsername(principal.getName());
    	List<UserEntity> users = userService.getAllUsers();
    	List<UserEntity> friends = userService.getAllFriends(principal.getName());
    	
    	// need a better way to remove the current user and their friends from the list of users
    	for(int i = 0; i < users.size(); i++)
    	{
    		// remove self
    		if(users.get(i).getUsername().compareTo(user.getUsername()) == 0 )
    		{
    			users.remove(users.get(i));
    		}
    		
    	}
    	for(int i = 0; i < users.size(); i++)
    	{
    		// remove friends
    		for(int j = 0; j < friends.size(); j++)
    		{
    			if(users.get(i).getUsername().compareTo(friends.get(j).getUsername()) == 0)
    			{
    				users.remove(users.get(i));
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
