package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String printWelcome(Model model)
	{
		model.addAttribute("message", "Welcome to CST-339 Topic 2 Activity!");
		return "home";
	}
	
	@RequestMapping("hello")
	@GetMapping("test2")
	public String goToTest2()
	{
		return "hello";
	}
}
