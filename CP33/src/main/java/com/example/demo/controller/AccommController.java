package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;

@Controller
@Setter
public class AccommController {

	@GetMapping("/page/accomm")
	public void accomm() {
	}
	//숙소 구분별로 5개 만들어야한다. URI 방식 ?
	
	@GetMapping("/page/detailAccomm")
	public void detailAccomm() {
	}
}
