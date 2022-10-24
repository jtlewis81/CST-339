package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/own-account")
public class OwnAccountController {

    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Your Account");
        model.addAttribute("pageName", "Account");
        
        return "own-account";
    }
}
