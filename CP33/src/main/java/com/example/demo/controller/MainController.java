package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.AccommDAO;
import com.example.demo.dao.EventDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.AccommVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class MainController {

	@Autowired
	private AccommDAO adao;
	
	@Autowired
	private EventDAO edao;
	
	@Autowired
	private MemberDAO mdao;
	
	@GetMapping("/")
	public String main(Model model) {
		List<AccommVO> popH = adao.popH();	// 호텔 인기숙소 목록 조회
		List<AccommVO> popP = adao.popP();	// 펜션 인기숙소 목록 조회
		List<AccommVO> popM = adao.popM();	// 모텔 인기숙소 목록 조회
		List<AccommVO> popF = adao.popF();	// 가족형숙소 인기숙소 목록 조회
		List<AccommVO> popB = adao.popB();	// 비지니스호텔 인기숙소 목록 조회
		model.addAttribute("popH1", popH.get(0));	// 호텔 평점 1위 숙소 상태유지
		model.addAttribute("popH2", popH.get(1));	// 호텔 평점 2위 숙소 상태유지
		model.addAttribute("popP1", popP.get(0));	// 펜션 평점 1위 숙소 상태유지
		model.addAttribute("popP2", popP.get(1));	// 펜션 평점 2위 숙소 상태유지
		model.addAttribute("popM1", popM.get(0));	// 모텔 평점 1위 숙소 상태유지
		model.addAttribute("popM2", popM.get(1));	// 모텔 평점 2위 숙소 상태유지
		model.addAttribute("popF1", popF.get(0));	// 가족형숙소 평점 1위 숙소 상태유지
		model.addAttribute("popF2", popF.get(1));	// 가족형숙소 평점 2위 숙소 상태유지
		model.addAttribute("popB1", popB.get(0));	// 비지니스호텔 평점 1위 숙소 상태유지
		model.addAttribute("popB2", popB.get(1));	// 비지니스호텔 평점 2위 숙소 상태유지
		model.addAttribute("list", edao.mainEvent());	// 진행중인 이벤트 목록 조회하여 상태유지
		return "/page/main";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		
		// 로그인 성공시 유저정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		// 로그인한 회원의 id 세션에 상태유지
		String id = user.getUsername();
		session.setAttribute("id", id);
		
		// 로그인한 회원의 role 세션에 상태유지
		String role = mdao.findRole(id);
		session.setAttribute("role", role);
		
		return "redirect:/";
	}
} 