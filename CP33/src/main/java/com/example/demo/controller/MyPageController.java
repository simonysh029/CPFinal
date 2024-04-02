package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dao.MyPageDAO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MyPageController {

	@Autowired
	private MemberDAO mdao;
	@Autowired
	private MyPageDAO mpdao;
	
	@GetMapping("/page/myPage")
	public void myPage(Model model, HttpSession session, String m_id) {
		m_id = (String)session.getAttribute("id");
		model.addAttribute("mList", mdao.findById(m_id));
		
		model.addAttribute("rList", mpdao.findReserve(m_id));
		System.out.println(mpdao.findReserve(m_id));
		
		model.addAttribute("sList", mpdao.findStay(m_id));
		System.out.println(mpdao.findStay(m_id));

	}
}
