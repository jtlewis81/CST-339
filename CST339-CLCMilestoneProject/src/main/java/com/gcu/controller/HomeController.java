package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.SecurityBusinessService;

@Controller
@RequestMapping("/home")
public class HomeController 
{
    @Autowired
    public SecurityBusinessService service; 
    
    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Home");
        model.addAttribute("pageName", "Home");
        model.addAttribute("posts", service.currentlyLoggedIn.getPosts()); 
        model.addAttribute("username", service.currentlyLoggedIn.getUsername());
        
        return "home";
    }
}