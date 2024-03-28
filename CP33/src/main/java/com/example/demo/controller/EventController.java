package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;

@Controller
@Setter
public class EventController {

	@GetMapping("/page/listEvent")
	public void listEvent() {		
	}
	@GetMapping("/page/detailEvent")
	public void detailEvent() {
	}
}
