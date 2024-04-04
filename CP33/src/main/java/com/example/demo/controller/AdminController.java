package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;

@Controller
@Setter
  
public class AdminController {

	@GetMapping("/admin/member")
	public void adminMember() {
	}
	@GetMapping("/admin/accomm")
	public void adminAccomm() {
	}
	@GetMapping("/admin/event")
	public void adminEvent() {
	}
}
   