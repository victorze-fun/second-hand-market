package com.victorze.secondhandmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.victorze.secondhandmarket.models.User;
import com.victorze.secondhandmarket.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}

	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/auth/login";
	}
	
}
