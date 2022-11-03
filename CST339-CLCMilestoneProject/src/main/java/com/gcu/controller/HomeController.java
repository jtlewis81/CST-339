package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< Updated upstream

@Controller
@RequestMapping("/home")
public class HomeController {

=======
import com.gcu.business.HomeBusinessService;
import com.gcu.business.HomeBusinessServiceInterface;
import com.gcu.business.SecurityBusinessService;

@Controller
@RequestMapping("/home")
public class HomeController 
{
    @Autowired
    public SecurityBusinessService service; 
    @Autowired
    public HomeBusinessServiceInterface home;
    
>>>>>>> Stashed changes
    @GetMapping("/")
    public String display(Model model) 
    {
        model.addAttribute("title", "Home");
        model.addAttribute("pageName", "Home");
<<<<<<< Updated upstream
=======
        //model.addAttribute("posts", service.currentlyLoggedIn.getPosts());
        model.addAttribute("posts", home.getPosts());
        model.addAttribute("username", service.currentlyLoggedIn.getUsername());
        
>>>>>>> Stashed changes
        return "home";
    }
}
