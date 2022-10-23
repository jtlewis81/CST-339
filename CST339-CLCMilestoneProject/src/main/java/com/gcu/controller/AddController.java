package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.AddModel;
import com.gcu.model.LoginModel;

@Controller
@RequestMapping("/add")
public class AddController {

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
        model.addAttribute("addModel", new AddModel());
        return "add";
    }
}
