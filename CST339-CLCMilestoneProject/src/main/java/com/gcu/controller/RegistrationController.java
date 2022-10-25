package com.gcu.controller;


import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.model.LoginModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	/**
	 * Display Registration page
	 * 
	 * @param model
	 * @return
	 */
    @GetMapping("/")
    public String displayRegistration(Model model) 
    {
<<<<<<< Updated upstream
        model.addAttribute("title", "Registration");
        model.addAttribute("userModel", new UserModel());
=======
        model.addAttribute("title", "Registration");       
        model.addAttribute("pageName", "Create Account");
        model.addAttribute("registrationModel", new RegistrationModel());
>>>>>>> Stashed changes
        return "registration";
    }
    
    /**
     * performs a submission of new user
     * 
     * @return
     */
    @PostMapping("/submitUser")
    public String submitUser(@Valid LoginModel loginModel, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("title", "Registration");      
            return "registration";
        }
        
        model.addAttribute("title", "Login");
        return "/user_account";
    }
    
    
    
}
