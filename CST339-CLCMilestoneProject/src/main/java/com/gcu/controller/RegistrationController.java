package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.Cst339ClcMilestoneProjectApplication;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.RegistrationModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/registration")
public class RegistrationController 
{
    @Autowired
    public SecurityBusinessService securityService;
    
	/**
	 * Display Registration page
	 * 
	 * @param model
	 * @return
	 */
    @GetMapping("/")
    public String displayRegistration(Model model) 
    {
        // Set attributes for Thymeleaf layout: registration.html 
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("title", "Registration");       
        model.addAttribute("pageName", "Create Account");
        model.addAttribute("registrationModel", new RegistrationModel());

        return "registration";
    }
    
    /**
     * performs a submission of new user
     * 
     * @return
     */
    @PostMapping("/submitRegistration")
    public ModelAndView submitRegistration(@Valid RegistrationModel registrationModel, BindingResult bindingResult, Model model) 
    {      
        ModelAndView mv = new ModelAndView(); 
        
        if (bindingResult.hasErrors()) 
        {
            mv.addObject("title", "Registration");
            mv.addObject("pageName", "Registration");
            mv.setViewName("registration");   
            return mv;
        }

        // Add new User to list of valid login credentials. 
        UserModel user = new UserModel(registrationModel.getUsername(), registrationModel.getPassword()); 
        Cst339ClcMilestoneProjectApplication.Users.add(user); 
        securityService.currentlyLoggedIn = user;
        
        mv.addObject("posts", securityService.currentlyLoggedIn.getPosts());
        mv.addObject("title", "Home");
        mv.addObject("pageName", "Home");
        mv.setViewName("home");

        return mv;
    }    
}