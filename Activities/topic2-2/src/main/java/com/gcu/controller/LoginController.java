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
import com.gcu.model.OrderModel;

@Controller
@RequestMapping("/login")
public class LoginController
{

	@GetMapping("/")
	public String display(Model model)
	{
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	
	
	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		// deprecated console output
		// System.out.println(String.format("Form with Username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()));
	
		// validate input for errors
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}
		
		// create orders
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.add(new OrderModel(0L, "00000001", "Item 1", 1.00f, 1));
		orders.add(new OrderModel(1L, "00000001", "Item 2", 2.00f, 2));
		orders.add(new OrderModel(2L, "00000001", "Item 3", 3.00f, 3));
		orders.add(new OrderModel(3L, "00000001", "Item 4", 4.00f, 4));
		orders.add(new OrderModel(4L, "00000001", "Item 5", 5.00f, 5));
		
		// return the orders view
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		return "orders";
	}
	
}
