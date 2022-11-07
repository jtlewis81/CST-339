package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.SecurityBusinessService;
import com.gcu.data.RegistrationDAOService;

@Controller
@RequestMapping("/home")
public class HomeController 
{
    @Autowired
    public SecurityBusinessService service; 
    
    @Autowired
    private RegistrationDAOService registrationService;
    
    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Home");
        model.addAttribute("pageName", "Home");
        model.addAttribute("posts", registrationService.GetUserPosts(service.currentlyLoggedIn)); 
        model.addAttribute("username", service.currentlyLoggedIn.getUsername());
        
        return "home";
    }
}