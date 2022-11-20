package com.gcu.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.business.PostBusinessService;
import com.gcu.business.UserBusinessService;

@Controller
@RequestMapping("/home")
public class HomeController 
{
    @Autowired
    private UserBusinessService userService;     
    @Autowired
    private PostBusinessService postService;
    
    @GetMapping("/")
    public String display(Model model, Principal principal) 
    {    	
        model.addAttribute("title", "Home");
        model.addAttribute("pageName", "Home");
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("posts", postService.getAllPostsByUser(userService.getUserByUsername(principal.getName())));
        
        return "home";
    }    
}
