package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Friends");
        
        return "friends";
    }
}
