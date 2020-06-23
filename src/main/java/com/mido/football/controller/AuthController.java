package com.mido.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	
	@GetMapping("/register")
	public String registerForm(Model model) {
		
		model.addAttribute("label", "Register");
		return "auth/register";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		
		
		model.addAttribute("label", "Login");
		return "auth/login";
	}
}
