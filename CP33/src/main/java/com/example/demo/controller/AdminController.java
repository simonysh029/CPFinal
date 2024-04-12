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
		return "/admin/listGuestroom";
	}
	
//회원 정보 입력, 수정, 삭제
	@GetMapping("/admin/insertMember")
	public void insertMemberForm() {
	}
	@PostMapping("/admin/insertMember")
	public String insertMember(MemberVO m) {
		m.setM_pw(passwordEncoder.encode(m.getM_pw()));
		mdao.insertAdmin(m);
		return "redirect:/admin/listMember";
	}
	@GetMapping("/admin/updateMember/{cid}")
	public String updateMember(@PathVariable("cid") String cid, Model model) {
		model.addAttribute("list", mdao.findById(cid));
		return "/admin/updateMember";
	}
	@PostMapping("/admin/updateMember")
	public String updateMemberForm(MemberVO m) {	
		m.setM_id(m.getM_id());
		m.setM_pw(passwordEncoder.encode(m.getM_pw()));
		mdao.updateMemberByAdmin(m); 
		return "redirect:/admin/listMember";
	}
	@GetMapping("/admin/deleteMember/{cid}")
	public String deleteMember(@PathVariable("cid") String cid) {
		mdao.deleteM(cid);
		return "redirect:/admin/listMember";
	}
	
//숙소 정보 입력, 수정, 삭제
	@GetMapping("/admin/insertAccomm")
	public void insertAccommForm() {
	}
	@PostMapping("/admin/insertAccomm")
	public String insertAccomm(AccommVO a) {
		adao.insertAccomm(a);
		return "redirect:/admin/listAccomm";
	}
	
	@GetMapping("/admin/updateAccomm/{aid}")
	public String updateAccommForm(@PathVariable("aid") String a_id, Model model) {
		model.addAttribute("aid", adao.findByAid(a_id));
		return "/admin/updateAccomm";
	}
	@PostMapping("/admin/updateAccomm")
	public String updateMember(AccommVO a) {
		adao.updateAccommByAdmin(a);
		return "redirect:/admin/listAccomm";
	}
	@GetMapping("/admin/deleteAccomm/{aid}")
	public String deleteAccomm(@PathVariable("aid") String a_id) {
		adao.deleteA(a_id);
		return "redirect:/admin/listAccomm";
	}
//이벤트 정보 입력, 수정, 삭제
	@GetMapping("/admin/insertEvent")
	public void insertEventForm(Model model) {
		model.addAttribute("e_no", edao.getNextNo());
	}	
	@PostMapping("/admin/insertEvent")
	public String insertEvent(EventVO e) {	
		edao.insertEvent(e);
		return "redirect:/admin/listEvent";
	}
	@GetMapping("/admin/updateEvent/{eno}")
	public String updateEventForm(@PathVariable("eno") int e_no, Model model) {
		model.addAttribute("eno", edao.findByNo(e_no));
		return "/admin/updateEvent";
	}
	@PostMapping("/admin/updateEvent")
	public String updateEvent(EventVO e) {
		edao.updateEventByAdmin(e);
		return "redirect:/admin/listEvent";
	}
	@GetMapping("/admin/deleteEvent/{eno}")
	public String deleteEvent(@PathVariable("eno") int e_no) {
		edao.deleteE(e_no);
		return "redirect:/admin/listEvent";
	}
	
	

	
		
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
   