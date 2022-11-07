package com.gcu.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.SecurityBusinessService;
import com.gcu.data.RegistrationDAOService;
import com.gcu.model.PostModel;

@Controller
@RequestMapping("/posts")
public class PostsController 
{
    @Autowired
    private SecurityBusinessService securityService;
    
    @Autowired 
    private RegistrationDAOService registrationService; 
    
    /**
     * Display 
     * 
     * @param model
     * @returns
     */
    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Add Post");     
        model.addAttribute("pageName", "Create Post");
        model.addAttribute("postModel", new PostModel()); 
        
        return "posts";
    }
    
    @PostMapping("/post")
    public ModelAndView addPost(@Valid PostModel postModel, BindingResult bindingResult, Model model) 
    {        
    	LocalDateTime timestamp = LocalDateTime.now();   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, M-dd-yyyy");
    	
        // Set PostModel properties. 
    	postModel.setTitle("n/a"); 
    	postModel.setImage("n/a");
        postModel.setTimestamp(timestamp.format(formatter)); 
        postModel.setUserId(securityService.currentlyLoggedIn.getId());
        postModel.setFriendsId(-1); 
        
        if (registrationService.InsertIntoPostsTable(postModel))
    		System.out.println("Post was successfully added to Posts table!");
        else 
        	System.out.println("An error occurred inserting new Post into Posts table.");


        ModelAndView homeView = new ModelAndView(); 
        homeView.addObject("posts", registrationService.GetUserPosts(securityService.currentlyLoggedIn));
        homeView.addObject("pageName", "Home");
        homeView.setViewName("home");
        
        return homeView;
    }
}