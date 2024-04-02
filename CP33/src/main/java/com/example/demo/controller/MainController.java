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
	
	@GetMapping("/")
	public String main(Model model) {
		List<AccommVO> popH = adao.popH();
		List<AccommVO> popP = adao.popP();
		List<AccommVO> popM = adao.popM();
		List<AccommVO> popF = adao.popF();
		List<AccommVO> popB = adao.popB();
		model.addAttribute("popH1", popH.get(0));
		model.addAttribute("popH2", popH.get(1));
		model.addAttribute("popP1", popP.get(0));
		model.addAttribute("popP2", popP.get(1));
		model.addAttribute("popM1", popM.get(0));
		model.addAttribute("popM2", popM.get(1));
		model.addAttribute("popF1", popF.get(0));
		model.addAttribute("popF2", popF.get(1));
		model.addAttribute("popB1", popB.get(0));
		model.addAttribute("popB2", popB.get(1));
		model.addAttribute("list", edao.mainEvent());
		return "/page/main";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String id = user.getUsername();
		session.setAttribute("id", id);
		return "redirect:/";
	}
	
}