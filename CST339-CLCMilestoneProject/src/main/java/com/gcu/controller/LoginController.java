package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
    {

        // if input errors, remain on login page and display error messages
        if(bindingResult.hasErrors()) {
            model.addAttribute("title", "Login Form");      
            return "login";
        }
        
        // temp registered users
    	List<UserModel> users = new ArrayList<UserModel>();
    	users.add(new UserModel("jlewis", "password"));
    	users.add(new UserModel("eperez", "password"));
    	users.add(new UserModel("jgooch", "password"));
    	users.add(new UserModel("ssuhail", "password"));
    	
        // if no errors, check for valid user
    	for(int i = 0; i < users.size(); i++)
    	{
    		// temp user data
    		UserModel user = users.get(i);
    		
    		// if the username and password match that of an existing user,
    		// continue to user's profile page (user_account)
    		if (user.getUsername().equals(loginModel.getUsername())
				&& user.getPassword().equals(loginModel.getPassword()) )
    		{
    			model.addAttribute("title", "Profile");
    			model.addAttribute("user", user);
    			return "user_account";
    		}
    	}
    	
    	// if user does not exist, go to registration page
    	return newUser(model);
    	
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
