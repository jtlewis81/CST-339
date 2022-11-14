package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.SecurityBusinessService;
import com.gcu.data.RegistrationDAOService;
import com.gcu.model.DeletePostModel;
import com.gcu.model.LoginModel;
import com.gcu.model.RegistrationModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/")
public class LoginController 
{
	@Autowired
	private SecurityBusinessService securityService;
	
	@Autowired
	private RegistrationDAOService registrationService;
	
	private List<UserModel> users = new ArrayList<UserModel>(); 
	
    /**
     * Display Login page
     * 
     * @param model
     * @return
     */
	@GetMapping("/")
	public String displayLogin(Model model)
	{
		// Return all users from Users table as list.
		this.users = registrationService.getAllUsers(); 
		
		model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
	}
    
    /**
     * Performs a login
     * 
     * @param loginModel
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
    {
    	// using bootstrap validation for blank fields
        
        // if valid user, continue to the user's home page
    	if (securityService.authenticate(this.users, loginModel.getUsername(), loginModel.getPassword()))
    	{
    		model.addAttribute("title", "Home");
			model.addAttribute("user", loginModel.getUsername());
            model.addAttribute("pageName", "Home");
            model.addAttribute("posts", registrationService.GetUserPosts(securityService.getCurrentlyLoggedIn()));
            model.addAttribute("deleteModel", registrationService.deleteModel);
            
			return "home";
    	}
    	
    	// if user does not exist
    	model.addAttribute("invalidError", "Invalid Login");
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
    
    
    @PostMapping("/delete")
    public ModelAndView delete(@Valid DeletePostModel deleteModel, BindingResult bindingResult, Model model)
    {        
    	System.out.print("Inside LoginController deletePost(" + deleteModel.getId() + ") ");
    	
    	ModelAndView mv = new ModelAndView(); 		
    	mv.addObject("title", "Home");
    	mv.addObject("pageName", "Home");
		mv.addObject("posts", registrationService.DeletePostById(deleteModel.getId()));
		mv.addObject("deleteModel", deleteModel);
        mv.setViewName("home");        
        return mv;
    }
}
