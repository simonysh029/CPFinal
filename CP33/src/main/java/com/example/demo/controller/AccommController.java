package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.AccommDAO;

import lombok.Setter;

@Controller
@Setter
public class AccommController {

	@Autowired
	private AccommDAO adao;
	
	@GetMapping("/page/accomm/{a_div}")
	public String accomm(Model model, @PathVariable("a_div") String a_div) {
		model.addAttribute("list", adao.listAcc(a_div));
		return "/page/accomm";
	}

	
	@GetMapping("/page/detailAccomm")
	public void detailAccomm() {
	}
}
