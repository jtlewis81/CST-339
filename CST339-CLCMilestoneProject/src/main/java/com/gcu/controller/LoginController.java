package com.gcu.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.Cst339ClcMilestoneProjectApplication;
import com.gcu.model.LoginModel;
import com.gcu.model.RegistrationModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/")
public class LoginController 
{
    /**
     * Display Login page
     * 
     * works for root or /login
     * 
     * @param model
     * @return
     */
	@GetMapping("/")
	public String displayLogin(Model model)
	{
		model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
	}
    
    /**
     * performs a login
     * 
     * @return
     */
    @PostMapping("/home")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
    {

    	// using bootstrap validation for blank fields
        
        // check for valid user
    	for(int i = 0; i < Cst339ClcMilestoneProjectApplication.Users.size(); i++)
    	{
    		// temp user data
    		UserModel user = Cst339ClcMilestoneProjectApplication.Users.get(i);
    		
    		// if the username and password match that of an existing user,
    		// continue to user's profile page (user_account)
    		if (user.getUsername().equals(loginModel.getUsername())
				&& user.getPassword().equals(loginModel.getPassword()) )
    		{
    			model.addAttribute("title", "Home");
    			model.addAttribute("user", user);
    			return "own-account";
    		}
    	}
    	
    	// if user does not exist
        return displayLogin(model);
    }
    
    /**
     * takes the user to the registration form
     * 
     * @param model
     * @return
     */
    @GetMapping("/registration")
    public String newUser(Model model)
    {
        model.addAttribute("title", "New User Registration");
        model.addAttribute("registrationModel", new RegistrationModel());
        return "registration";
    }
}
