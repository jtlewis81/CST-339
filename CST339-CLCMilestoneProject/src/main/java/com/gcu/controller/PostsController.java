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

import com.gcu.Cst339ClcMilestoneProjectApplication;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.PostModel;
import com.gcu.model.RegistrationModel;
import com.gcu.model.UserModel;

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
        model.addAttribute("posts", securityService.SamplePosts); 
        
        return "posts";
    }
    
    @PostMapping("/post")
    public String addPost(@Valid PostModel postModel, BindingResult bindingResult, Model model) 
    {
        // Set PostModel properties. 
        LocalDateTime timestamp = LocalDateTime.now();         
        postModel.setTimestamp(timestamp); 
        postModel.setUsername(securityService.currentlyLoggedIn.getUsername());
        
        // Print console debugging logs. 
        System.out.println("Username: " + postModel.getUsername());
        System.out.println("Content:\n" + postModel.getContent());
        System.out.println("Timestamp: " + postModel.getTimestamp());
        
//        if (bindingResult.hasErrors()) 
//        {
//            model.addAttribute("title", "Registration");      
//            return "post";
//        }

        // Add new User to list of valid login credentials. 
        securityService.SamplePosts.add(0, postModel); 

        model.addAttribute("post", postModel); 
        
        return "post";
    }
}