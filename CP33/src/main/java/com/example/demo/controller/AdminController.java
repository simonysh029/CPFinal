package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.AccommDAO;
import com.example.demo.dao.EventDAO;
import com.example.demo.dao.GuestroomDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.GuestroomVO;

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
	@Autowired
	private GuestroomDAO gdao;
	
	@GetMapping("/admin/member")
	public void listMember(Model model) {
		model.addAttribute("mList", mdao.findAll());	
	}
	@GetMapping("/admin/accomm")
	public void listAccomm(Model model) {
		model.addAttribute("aList", adao.findAllA());
	}
	@GetMapping("/admin/event")
	public void listEvent(Model model) {
		model.addAttribute("eList", edao.findAllE());
	}
	
	@GetMapping("/admin/guestroom/{a_id}")
	public String listGuestroom(@PathVariable("a_id") String a_id, Model model) {
		List<GuestroomVO> gList = gdao.findByaId(a_id);
		System.out.println(a_id);
		model.addAttribute("gList", gList);
		String view = "/admin/listGuestroom";
		return view;
	}
}
   