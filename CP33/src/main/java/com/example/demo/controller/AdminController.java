package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;

@Controller
@Setter
public class AdminController {

	@GetMapping("/admin/main")
	public void adminMain() {
	}
}
