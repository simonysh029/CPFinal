package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	
	
	@GetMapping("/")
	public String main(Model model) {
		return "/page/main";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String id = user.getUsername();
		session.setAttribute("id", id);
		return "/page/main";
	}
	
	@GetMapping("/page/myPage")
	public void myPage() {
	}
}
