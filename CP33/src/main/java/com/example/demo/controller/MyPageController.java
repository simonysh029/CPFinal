package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dao.MyPageDAO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MyPageController {

	@Autowired
	private MemberDAO mdao;
	@Autowired
	private MyPageDAO mpdao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/page/myPage")
	public void myPage(Model model, HttpSession session, String username) {
		username = (String)session.getAttribute("id");
		model.addAttribute("mList", mdao.findById(username));
		model.addAttribute("rList", mpdao.findReserve(username));
		System.out.println(mpdao.findReserve(username));
		model.addAttribute("sList", mpdao.findStay(username));
		System.out.println(mpdao.findStay(username));
	}
	@GetMapping("/page/updateMember")
	public void updateForm() {
	}
	@PostMapping("/page/updateMember")
	public String updateMember(MemberVO m, String username, String password, String m_name, String m_phone, String m_addr) {
		
		m.setM_id(username);
		m.setM_pw(passwordEncoder.encode(password));
		m.setM_name(m_name);
		m.setM_phone(m_phone);
		m.setM_addr(m_addr);		
		mdao.updateMember(m);
		
		//URL만 어디로 보낼지 생각
		
		return "redirect:/";
	}

}
