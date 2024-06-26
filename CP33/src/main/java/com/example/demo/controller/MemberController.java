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
	
	// 로그인 화면으로 이동
	@GetMapping("/page/login")
	public void loginForm() {
	}
	
	// 회원가입 화면으로 이동
	@GetMapping("/page/join")
	public void joinForm() {	
	}
	
	// 회원가입 insert
	@PostMapping("/page/join")
	public String joinSubmit(MemberVO m) {
		m.setM_pws(m.getM_pw());
		m.setM_pw(passwordEncoder.encode(m.getM_pw()));
		m.setM_mile(0);
		dao.insert(m);
		String view = "/page/login";
		return view;
	}
	
	// 아이디 찾기 화면으로 이동
	@GetMapping("/page/idFind")
	public void idFindForm() {
	}
	
	// 비밀번호 찾기 화면으로 이동
	@GetMapping("/page/pwFind")
	public void pwFindForm() {
	}
	
	// 아이디 중복확인
	@PostMapping("/page/idCheck")
	@ResponseBody
	public String idCheck(String m_id) {
		String id = dao.idCheck(m_id);
		if(id == null) {
			return "'" + m_id + "'는(은) 사용 가능한 ID입니다.";
		}else {
			return "'" + m_id + "'는(은) 사용할 수 없는 ID입니다.";
		}
	}
	
	// 아이디 찾기
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
	
	// 비밀번호 찾기
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