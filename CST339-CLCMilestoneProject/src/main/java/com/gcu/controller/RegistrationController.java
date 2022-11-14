package com.gcu.controller;

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
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/registration")
public class RegistrationController 
{
    @Autowired
    public SecurityBusinessService securityService;
    @Autowired
    private RegistrationDAOService registrationService;
    
    private static List<String> usernames; 
    
	/**
	 * Display Registration page
	 * 
	 * @param model
	 * @return
	 */
    @GetMapping("/")
    public String displayRegistration(Model model) 
    { 
    	// Retrieve all existing Usernames from Users database table. 
    	usernames = registrationService.getAllUsernames(); 
    	
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("title", "Registration");       
        model.addAttribute("pageName", "Create Account");
        
        return "registration";
    }
    
    /**
     * performs a submission of new user
     * 
     * @return
     */
    @PostMapping("/submitRegistration")
    public ModelAndView submitRegistration(@Valid UserModel userModel, BindingResult bindingResult, Model model) 
    {      
        ModelAndView mv = new ModelAndView(); 
        
        // Check if Username already exists.
        boolean existingUserError = usernames.contains(userModel.getUsername());
        
        if (bindingResult.hasErrors() || existingUserError) 
        {
        	if (existingUserError)
        		mv.addObject("existingUserError", "Username already exists!"); 
        	
            mv.addObject("title", "Registration");
            mv.addObject("pageName", "Registration");
            mv.setViewName("registration");   
            return mv;
        }

        // Add new User to list of valid login credentials. 
        UserModel insertionResult = registrationService.InsertIntoUsersTable(userModel).get(1);
        if (insertionResult != null)
        {
        	System.out.println("New user successfully added to Users table!"); 
        	System.out.println("ID = " + insertionResult.getId() + ", Username = " + insertionResult.getUsername());
        	securityService.setCurrentlyLoggedIn(insertionResult);
        }

        mv.addObject("posts", registrationService.GetUserPosts(securityService.getCurrentlyLoggedIn()));
        mv.addObject("title", "Home");
        mv.addObject("pageName", "Home");
        mv.addObject("deleteModel", registrationService.deleteModel);
        mv.setViewName("home");
        return mv;
    }    
    
    @PostMapping("/delete")
    public ModelAndView delete(@Valid DeletePostModel deleteModel, BindingResult bindingResult, Model model)
    {        
    	System.out.print("Inside RegistrationController deletePost(" + deleteModel.getId() + ") ");
    	
    	ModelAndView mv = new ModelAndView(); 		
    	mv.addObject("title", "Home");
    	mv.addObject("pageName", "Home");
		mv.addObject("posts", registrationService.DeletePostById(deleteModel.getId()));
		mv.addObject("deleteModel", deleteModel);
        mv.setViewName("home");        
        return mv;
    }
}
