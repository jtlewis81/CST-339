package com.gcu.controller;

import java.time.LocalDateTime;

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
import com.gcu.model.PostModel;

@Controller
@RequestMapping("/posts")
public class PostsController 
{
    @Autowired
    private SecurityBusinessService securityService;
    
    /**
     * Display 
     * 
     * @param model
     * @return
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
        // Set PostModel properties. 
        LocalDateTime timestamp = LocalDateTime.now();         
        postModel.setTimestamp(timestamp); 
        postModel.setUsername(securityService.currentlyLoggedIn.getUsername());
        
        // Print console debugging logs. 
        System.out.println("Username: " + postModel.getUsername());
        System.out.println("Content:\n" + postModel.getContent());
        System.out.println("Timestamp: " + postModel.getTimestamp());

        // Add new User to list of valid login credentials. 
        securityService.currentlyLoggedIn.addPost(postModel);

        ModelAndView homeView = new ModelAndView(); 
        homeView.addObject("posts", securityService.currentlyLoggedIn.getPosts());
        homeView.addObject("pageName", "Home");
        homeView.setViewName("home");
        
        return homeView;
    }
}