package com.gcu.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.Cst339ClcMilestoneProjectApplication;
import com.gcu.model.RegistrationModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/registration")
public class RegistrationController 
{
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
    public String submitRegistration(@Valid RegistrationModel registrationModel, BindingResult bindingResult, Model model) 
    {
        if (bindingResult.hasErrors()) 
        {
            model.addAttribute("title", "Registration");      
            return "registration";
        }

        // Add new User to list of valid login credentials. 
        Cst339ClcMilestoneProjectApplication.Users.add(new UserModel(registrationModel.getUsername(), registrationModel.getPassword())); 

        // Set attributes for Thymeleaf layout: registrationSuccess.html
        model.addAttribute("user", registrationModel); 

        return "registrationSuccess";
    }    
}