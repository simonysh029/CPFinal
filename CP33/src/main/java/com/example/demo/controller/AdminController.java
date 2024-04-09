package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.AccommDAO;
import com.example.demo.dao.EventDAO;
import com.example.demo.dao.GuestroomDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.AccommVO;
import com.example.demo.vo.EventVO;
import com.example.demo.vo.GuestroomVO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpSession;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	//회원, 숙소, 이벤트, 객실 리스트
	
	@GetMapping("/admin/listMember")
	public void listMember(Model model) {
		model.addAttribute("mList", mdao.findAll());	
	}
	@GetMapping("/admin/listAccomm")
	public void listAccomm(Model model) {
		model.addAttribute("aList", adao.findAllA());
	}
	@GetMapping("/admin/listEvent")
	public void listEvent(Model model) {
		model.addAttribute("eList", edao.findAllE());
	}
	@GetMapping("/admin/listGuestroom/{a_id}")
	public String listGuestroom(@PathVariable("a_id") String a_id, Model model) {
		List<GuestroomVO> gList = gdao.findByaId(a_id);
		System.out.println(a_id);
		model.addAttribute("gList", gList);
		String view = "/admin/listGuestroom";
		return view;
	}
	
	//회원 정보 입력, 수정, 삭제
	
	@GetMapping("/admin/insertMember")
	public void insertMemberForm() {
	}
//	@PostMapping("/admin/insertMember")
//	public String insertMember(MemberVO m) {
//		mdao.insert(m);
//		return "/admin/listMember";
//	}
	
	@GetMapping("/admin/updateMember")
	public void updateMemberForm() {		
	}
//	@PostMapping("/admin/updateMember")
//	public String updateMember(MemberVO m, HttpSession session) {
//		String u_id = session.getId();
//		m.setM_id(u_id);
//		m.setM_pw(passwordEncoder.encode(m.getM_pw()));
//		mdao.updateMemberByAdmin(m); 
//		return "/admin/listMember";
//	}

	//	@GetMapping("/admin/deleteMember")
//	public void deleteMember(String m_id) {
//		
//	}
//	
//	//숙소 정보 입력, 수정, 삭제
//	
	@GetMapping("/admin/insertAccomm")
	public void insertAccommForm() {
	}
//	@PostMapping("/admin/insertAccomm")
//	public String insertAccomm(AccommVO avo) {
//		
//		return "/admin/listAccomm";
//	}
	@GetMapping("/admin/updateAccomm")
	public void updateAccommForm() {
	}
//	@PostMapping("/admin/updateAccomm")
//	public String updateMember(AccommVO avo) {
//		
//		return "/admin/listAccomm";
//	}
//	@GetMapping("/admin/deleteAccomm")
//	public void deleteAccomm(String a_id) {
//		
//	}
//
//	//이벤트 정보 입력, 수정, 삭제
//	
	@GetMapping("/admin/insertEvent")
	public void insertEventForm() {
	}
//	@PostMapping("/admin/insertEvent")
//	public String insertEvent(EventVO evo) {
//		
//		return "/admin/event";
//	}
	@GetMapping("/admin/updateEvent")
	public void updateEventForm() {
	}
//	@PostMapping("/admin/updateEvent")
//	public String updateEvent(EventVO evo) {
//		
//		return "/admin/event";
//	}
//	@GetMapping("/admin/deleteEvent")
//	public void deleteEvent(int e_no) {
//		
//	}
//	
//	//객실 정보 입력, 수정, 삭제
//	
	@GetMapping("/admin/insertGuestroom")
	public void insertGuestroomForm() {
	}
//	@PostMapping("/admin/insertGuestroom")
//	public String insertGuestroom(GuestroomVO gvo) {
//		
//		return "/admin/guestroom";
//	}
	@GetMapping("/admin/updateGuestroom")
	public void updateGuestroomForm() {
	}
//	@PostMapping("/admin/updateGuestroom")
//	public String updateGuestroom(GuestroomVO gvo) {
//		
//		return "/admin/guestroom";
//	}
//	@GetMapping("/admin/deleteGuestroom")
//	public void deleteGuestrooom(String g_id) {
//		
//	}
//	
	
}
   