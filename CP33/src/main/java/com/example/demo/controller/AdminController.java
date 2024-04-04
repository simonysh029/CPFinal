package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.AccommDAO;
import com.example.demo.dao.EventDAO;
import com.example.demo.dao.MemberDAO;

import lombok.Setter;

@Controller
@Setter
  
public class AdminController {

	@Autowired
	private MemberDAO mdao;
	@Autowired
	private AccommDAO adao;
	@Autowired
	private EventDAO edao;
	
	@GetMapping("/admin/member")
	public void adminMember(Model model) {
		model.addAttribute("mList", mdao.findAll());	
	}
	@GetMapping("/admin/accomm")
	public void adminAccomm(Model model) {
		model.addAttribute("aList", adao.findAllA());
	}
	@GetMapping("/admin/event")
	public void adminEvent(Model model) {
		model.addAttribute("eList", edao.findAllE());
	}
}
   