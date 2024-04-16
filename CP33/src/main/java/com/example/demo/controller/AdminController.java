package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AccommDAO;
import com.example.demo.dao.EventDAO;
import com.example.demo.dao.GuestroomDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.AccommVO;
import com.example.demo.vo.EventVO;
import com.example.demo.vo.GuestroomVO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
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
	
//회원 정보 입력, 수정, 삭제
	@GetMapping("/admin/listMember")
	public void listMember(Model model) {
		model.addAttribute("mList", mdao.findAll());	
	}
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
		m.setM_pws(m.getM_pw());
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
	@GetMapping("/admin/listAccomm")
	public void listAccomm(Model model) {
		model.addAttribute("aList", adao.findAllA());
	}
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
	@GetMapping("/admin/listEvent")
	public void listEvent(Model model) {
		model.addAttribute("eList", edao.findAllE());
	}
	@GetMapping("/admin/insertEvent")
	public void insertEventForm(Model model) {
		model.addAttribute("e_no", edao.getNextNo());
	}
	@PostMapping("/admin/insertEvent")
	public ModelAndView insertEvent(EventVO e, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		String e_all = null;
		MultipartFile uploadFileAll = e.getUploadFileAll();
		e_all = uploadFileAll.getOriginalFilename();
		if(e_all != null && !e_all.equals("")) {
			try {
				byte []data = uploadFileAll.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+e_all);
				fos.write(data);
				fos.close();
				e.setE_all(e_all);
			} catch (Exception er) {
				System.out.println("예외발생:"+er.getMessage());
			}
		}
		
		String e_each = null;
		MultipartFile uploadFileEach = e.getUploadFileEach();
		e_each = uploadFileEach.getOriginalFilename();
		if(e_each != null && !e_each.equals("")) {
			try {
				byte []data = uploadFileEach.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+e_each);
				fos.write(data);
				fos.close();
				e.setE_each(e_each);
			} catch (Exception er) {
				System.out.println("예외발생:"+er.getMessage());
			}
		}
		edao.insertEvent(e);	
		ModelAndView mav = new ModelAndView("redirect:/admin/listEvent");
		return mav;
	}
	@GetMapping("/admin/updateEvent/{eno}")
	public String updateEventForm(@PathVariable("eno") int e_no, Model model) {
		model.addAttribute("eno", edao.findByNo(e_no));
		return "/admin/updateEvent";
	}
	@PostMapping("/admin/updateEvent")
	public ModelAndView updateEvent(EventVO e, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		String e_all = null;
		MultipartFile uploadFileAll = e.getUploadFileAll();
		e_all = uploadFileAll.getOriginalFilename();
		if(e_all != null && !e_all.equals("")) {
			try {
				byte []data = uploadFileAll.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+e_all);
				fos.write(data);
				fos.close();
				e.setE_all(e_all);
			} catch (Exception er) {
				System.out.println("예외발생:"+er.getMessage());
			}
		}
		String e_each = null;
		MultipartFile uploadFileEach = e.getUploadFileEach();
		e_each = uploadFileEach.getOriginalFilename();
		if(e_each != null && !e_each.equals("")) {
			try {
				byte []data = uploadFileEach.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+e_each);
				fos.write(data);
				fos.close();
				e.setE_each(e_each);
			} catch (Exception er) {
				System.out.println("예외발생:"+er.getMessage());
			}
		}
		edao.updateEventByAdmin(e);
		ModelAndView mav = new ModelAndView("redirect:/admin/listEvent");
		return mav;
	}
	@GetMapping("/admin/deleteEvent/{eno}")
	public String deleteEvent(@PathVariable("eno") int e_no) {
		edao.deleteE(e_no);
		return "redirect:/admin/listEvent";
	}
//객실 정보 입력, 수정, 삭제
	@GetMapping("/admin/listGuestroom/{a_id}")
	public String listGuestroom(@PathVariable("a_id") String a_id, Model model) {
		List<GuestroomVO> gList = gdao.findByaId(a_id);
		AccommVO a = adao.findByAid(a_id);
		model.addAttribute("name", a.getA_name());
		model.addAttribute("id", a_id);
		model.addAttribute("gList", gList);
		return "/admin/listGuestroom";
	}
	@GetMapping("/admin/insertGuestroom/{a_id}")
	public String insertGuestroomForm(@PathVariable("a_id") String a_id, Model model) {
		model.addAttribute("a_id", a_id);
		return "/admin/insertGuestroom";
	}
	@PostMapping("/admin/insertGuestroom")
	public String insertGuest(GuestroomVO g) {	
		String aid = g.getA_id();
		gdao.insertGuest(g);
		System.out.println(g);
		return "redirect:/admin/listGuestroom/"+aid;
	}
	
	@GetMapping("/admin/updateGuestroom/{g_id}")
	public String updateGuestroomForm(@PathVariable("g_id") String g_id, Model model) {
		model.addAttribute("gid", gdao.findByGid(g_id));
		GuestroomVO g = gdao.findByGid(g_id);
		String a_id = g.getA_id();
		model.addAttribute("a_id", a_id);
		return "/admin/updateGuestroom";
	}
	@PostMapping("/admin/updateGuestroom")
	public String updateGuestroom(GuestroomVO g) {		
		gdao.updateGuestroomByAdmin(g);
		String a_id = g.getA_id();
		return "redirect:/admin/listGuestroom/"+a_id;
	}
	
	@GetMapping("/admin/deleteGuestroom/{g_id}")
	public String deleteGuestrooom(@PathVariable("g_id") String g_id) {
		GuestroomVO g = gdao.findByGid(g_id);
		String a_id = g.getA_id();
		gdao.deleteG(g_id);
		return "redirect:/admin/listGuestroom/"+a_id;
	}
	
	
}
   