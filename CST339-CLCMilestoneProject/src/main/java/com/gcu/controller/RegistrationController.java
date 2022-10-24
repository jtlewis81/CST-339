package com.gcu.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.gcu.model.LoginModel;
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
        model.addAttribute("title", "Registration");
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
        if(bindingResult.hasErrors()) {
            model.addAttribute("title", "Registration");      
            return "registration";
        }
        
        model.addAttribute("firstName", registrationModel.getFirstName());
        model.addAttribute("lastName", registrationModel.getLastName());
        model.addAttribute("email", registrationModel.getEmail());
        model.addAttribute("phone", registrationModel.getPhone());
        model.addAttribute("username", registrationModel.getUsername());
        model.addAttribute("password", registrationModel.getPassword());
        return "registrationSuccess";
    }
    
}
