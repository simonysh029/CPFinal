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
	
	// 숙소 조회 목록
	@GetMapping("/page/accomm")
	public String accomm(Model model, String a_div, int g_person, String keyword) {
		model.addAttribute("list", adao.listAcc(a_div,g_person,keyword));
		return "/page/accomm";
	}

	// 숙소 상세
	@GetMapping("/page/detailAccomm/{a_id}")
	public String detailAccomm(Model model, @PathVariable("a_id") String a_id) {
		model.addAttribute("detail1", adao.detailAcc1(a_id));
		model.addAttribute("detail2", adao.detailAcc2(a_id));
		model.addAttribute("a_id", a_id);
		return "/page/detailAccomm";
	}
}
