package com.gcu.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gcu.business.PostBusinessService;
import com.gcu.business.UserBusinessService;
import com.gcu.data.entity.PostEntity;
import com.gcu.data.entity.UserEntity;

@Controller
@RequestMapping("/profile")
public class ProfileController
{
	// VARIABLES 
    @Autowired
    private UserBusinessService userService;     
    @Autowired
    private PostBusinessService postService;
	
    /**
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/self")
    public String displayOwnProfile(Model model, Principal principal)
    {
    	UserEntity user = userService.getUserByUsername(principal.getName());
    	List<PostEntity> posts = postService.getAllPostsByUser(user);
    	List<UserEntity> friends = userService.getAllFriends(principal.getName()); // <<<<<<<< NEEDS UPDATED WITH PROPER METHOD
    	
    	model.addAttribute("title", "MyProfile");
    	model.addAttribute("pageName", "My Profile");
    	model.addAttribute("username", principal.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("posts", posts);
    	model.addAttribute("friends", friends);
    	return "my-profile";
    }
    
    /**
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/user")
    public String getUserProfileByUsername(@RequestParam String username, Model model, Principal principal)
    {
    	UserEntity user = userService.getUserByUsername(username);
    	List<PostEntity> posts = postService.getAllPostsByUser(user);
    	List<UserEntity> friends = new ArrayList<UserEntity>(); // <<<<<<<< NEEDS UPDATED WITH PROPER METHOD
    	
    	model.addAttribute("title", "User Profile");
    	model.addAttribute("pageName", user.getUsername() + "'s Profile");
    	model.addAttribute("username", principal.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("posts", posts);
    	model.addAttribute("friends", friends);
    	
    	return "user-profile";
    }
    
    @GetMapping("/addFriend")
    public String addFriend(@RequestParam String username, Model model, Principal principal)
    {    	
    	try
    	{
    		userService.addFriend(principal.getName(), username);
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return "redirect:/home";
    }
    
}
