package com.example.demo.controller;

import java.io.File;
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
		m.setM_pws(m.getM_pw());
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
	public ModelAndView insertAccomm(AccommVO a, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		String a_img = null;
		MultipartFile uploadFileAcc = a.getUploadFileAcc();
		a_img = uploadFileAcc.getOriginalFilename();
		if(a_img != null && !a_img.equals("")) {
			try {
				byte [] data = uploadFileAcc.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+a_img);
				fos.write(data);
				fos.close();
				a.setA_img(a_img);
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		adao.insertAccomm(a);
		ModelAndView mav = new ModelAndView("redirect:/admin/listAccomm");
		return mav;
	}
	@GetMapping("/admin/updateAccomm/{aid}")
	public String updateAccommForm(@PathVariable("aid") String a_id, Model model) {
		model.addAttribute("aid", adao.findByAid(a_id));
		return "/admin/updateAccomm";
	}
	@PostMapping("/admin/updateAccomm")
	public ModelAndView updateMember(AccommVO a, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		String a_img = null;
		MultipartFile uploadFileAcc = a.getUploadFileAcc();
		a_img = uploadFileAcc.getOriginalFilename();
		if(a_img != null && !a_img.equals("")) {
			try {
				byte [] data = uploadFileAcc.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+a_img);
				fos.write(data);
				fos.close();
				a.setA_img(a_img);
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		adao.updateAccommByAdmin(a);
		ModelAndView mav = new ModelAndView("redirect:/admin/listAccomm");
		return mav;
	}
	@GetMapping("/admin/deleteAccomm/{aid}")
	public String deleteAccomm(@PathVariable("aid") String a_id, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		String a_img = adao.findByAid(a_id).getA_img();
		int re = adao.deleteA(a_id);
		if(re==1) {
			File file = new File(path+"/"+a_img);
			file.delete();
		}
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
	@GetMapping("/admin/deleteEvent/{e_no}")
	public String deleteEvent(@PathVariable("e_no") int e_no, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		String e_all = edao.findByNo(e_no).getE_all();
		String e_each = edao.findByNo(e_no).getE_each();
		int re = edao.deleteE(e_no);
		int ree = edao.deleteE(e_no);
		if(re==1 && ree==1) {
			File fileAll = new File(path+"/"+e_all);
			File fileEach = new File(path+"/"+e_each);
			fileAll.delete();
			fileEach.delete();
		}
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
	public ModelAndView insertGuest(GuestroomVO g, HttpServletRequest request) {
		String aid = g.getA_id();		
		String path = request.getServletContext().getRealPath("images");
		String g_img = null;
		MultipartFile uploadFileGuest = g.getUploadFileGuest();
		g_img = uploadFileGuest.getOriginalFilename();
		if (g_img != null && !g_img.equals("")) {
			try {
				byte []data = uploadFileGuest.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+g_img);
				fos.write(data);
				fos.close();
				g.setG_img(g_img);
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}		
		gdao.insertGuest(g);
		ModelAndView mav = new ModelAndView("redirect:/admin/listGuestroom/"+aid);
		return mav;
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
	public String updateGuestroom(GuestroomVO g, HttpServletRequest request) {		
		String path = request.getServletContext().getRealPath("images");
		String g_img = null;
		MultipartFile uploadFileGuest = g.getUploadFileGuest();
		g_img = uploadFileGuest.getOriginalFilename();
		if (g_img != null && !g_img.equals("")) {
			try {
				byte []data = uploadFileGuest.getBytes();
				FileOutputStream fos = new FileOutputStream(path+"/"+g_img);
				fos.write(data);
				fos.close();
				g.setG_img(g_img);
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		String a_id = g.getA_id();
		gdao.updateGuestroomByAdmin(g);
		return "redirect:/admin/listGuestroom/"+a_id;
	}
	
	@GetMapping("/admin/deleteGuestroom/{g_id}")
	public String deleteGuestrooom(@PathVariable("g_id") String g_id, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("images");
		GuestroomVO g = gdao.findByGid(g_id);
		String a_id = g.getA_id();
		String g_img = gdao.findByGid(g_id).getG_img();
		int re = gdao.deleteG(g_id);
		if(re==1) {
			File file = new File(path+"/"+g_img);
			file.delete();
		}
		return "redirect:/admin/listGuestroom/"+a_id;
	}	
}
   