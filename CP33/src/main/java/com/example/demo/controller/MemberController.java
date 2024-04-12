package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import lombok.Setter;

@Controller
@Setter
public class MemberController {

	@Autowired
	private MemberDAO dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/page/login")
	public void loginForm() {
	}
	
	@GetMapping("/page/join")
	public void joinForm() {	
	}
	
	@PostMapping("/page/join")
	public String joinSubmit(MemberVO m) {
		m.setM_pws(m.getM_pw());
		m.setM_pw(passwordEncoder.encode(m.getM_pw()));
		m.setM_mile(0);
		dao.insert(m);
		String view = "/page/login";
		return view;
	}
	
	@GetMapping("/page/idFind")
	public void idFindForm() {
	}
	
	@GetMapping("/page/pwFind")
	public void pwFindForm() {
	}
	
	@PostMapping("/page/idFind")
	@ResponseBody
	public String idFind(MemberVO m) {
		String id = dao.idFind(m);
		if(id == null) {
			return "일치하는 아이디가 없습니다.";
		}else {
			return "회원님의 아이디는 '" + id + "' 입니다.";
		}
	}
	
	@PostMapping("/page/pwFind")
	@ResponseBody
	public String pwFind(MemberVO m) {
		String pw = dao.pwFind(m);
		if(pw == null) {
			return "일치하는 비밀번호가 없습니다.";
		}else {
			return "회원님의 비밀번호는 '" + pw + "' 입니다.";
		}
	}
}