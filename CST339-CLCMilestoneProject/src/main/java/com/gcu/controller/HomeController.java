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

import com.gcu.business.SecurityBusinessService;
import com.gcu.data.RegistrationDAOService;
import com.gcu.model.DeletePostModel;

@Controller
@RequestMapping("/home")
public class HomeController 
{
    @Autowired
    public SecurityBusinessService securityService;     
    @Autowired
    private RegistrationDAOService registrationService;
    
    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Home");
        model.addAttribute("pageName", "Home");
        model.addAttribute("posts", registrationService.GetUserPosts(securityService.getCurrentlyLoggedIn())); 
        model.addAttribute("username", securityService.getCurrentlyLoggedIn().getUsername());
        model.addAttribute("deleteModel", registrationService.deleteModel);
        
        return "home";
    }

    @PostMapping("/delete")
    public ModelAndView delete(@Valid DeletePostModel deleteModel, BindingResult bindingResult, Model model)
    {        
    	System.out.print("Inside HomeController deletePost(" + deleteModel.getId() + ") ");
    	
    	ModelAndView mv = new ModelAndView(); 		
    	mv.addObject("title", "Home");
    	mv.addObject("pageName", "Home");
		mv.addObject("posts", registrationService.DeletePostById(deleteModel.getId()));
		mv.addObject("deleteModel", deleteModel);
        mv.setViewName("home");        
        return mv;
    }

}
