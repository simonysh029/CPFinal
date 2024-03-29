package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
	public EventDAO dao;
	@Autowired
	public EventVO vo;
	
	//넘어가면서 list값 넣어 변환
	@GetMapping("/page/listEvent")
	public List<EventVO> listEvent() { 
		return dao.findAll();
	}

	//uri방식으로 받음
	@GetMapping("/page/detailEvent/{e_no}")
	public String detailEvent(@PathVariable("e_no") int e_no) {
		return "/page/detailEvent";
	}
	
}
