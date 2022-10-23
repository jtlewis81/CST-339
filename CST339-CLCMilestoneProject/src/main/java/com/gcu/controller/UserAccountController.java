package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user_account")
public class UserAccountController {

    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Your Account");
        
        return "user_account";
    }
}
