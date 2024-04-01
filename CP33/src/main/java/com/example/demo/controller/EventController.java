package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.EventDAO;
import com.example.demo.vo.EventVO;

import lombok.Setter;

@Controller
@Setter
public class EventController {

	@Autowired
	private EventDAO edao;
	
	//넘어가면서 list값 넣어 변환
	@GetMapping("/page/listEvent")
	public void listEvent(Model model) {
		model.addAttribute("list", edao.listEvent());
	}

	//uri방식으로 받음
	@GetMapping("/page/detailEvent/{e_no}")
	public String detailEvent(@PathVariable("e_no") int e_no, EventVO e, Model model) {
		EventVO eList = edao.findByNo(e_no);
		model.addAttribute("a", eList);
		String view = "/page/detailEvent";
		return view;
	}
	
}