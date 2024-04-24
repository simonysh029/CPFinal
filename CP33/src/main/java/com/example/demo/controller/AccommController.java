package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AccommDAO;

import lombok.Setter;

@Controller
@Setter
public class AccommController {

		@Autowired
		private AccommDAO adao;
		
		private int pageSIZE = 3;
		private int totalRecord;
		private int totalPage;
		
		// 숙소 검색, 조회
		@GetMapping("/page/accomm")
		public ModelAndView accomm(
				@RequestParam(value = "a_div", required = false, defaultValue = "all") String a_div,
	            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
	            @RequestParam(value = "g_person", required = false, defaultValue = "0") int g_person,
	            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
	            ModelAndView modelAndView
				) {
			
			// 페이징 처리를 위한 변수 생성, 계산
			totalRecord = adao.getTotalRecord(a_div, g_person, keyword);
			totalPage = (int)Math.ceil(totalRecord/(double)pageSIZE);
			int offset = (pageNum - 1) * pageSIZE;
			int limit = pageSIZE;
			
			// 조건에 따른 숙소 목록 조회하여 상태유지
			modelAndView.addObject("list", adao.listAcc(a_div,g_person,keyword,offset,limit));
			
			// 조회된 조건 유지한 상태로 해당 페이지에 맞는 데이터 가져오기 위한 변수 상태유지
			modelAndView.addObject("a_div", a_div);
			modelAndView.addObject("keyword", keyword);
			modelAndView.addObject("g_person", g_person);
			modelAndView.addObject("totalPage", totalPage);
			
			// 현재 보고있는 페이지 이벤트 적용을 위해 페이지번호 상태유지
			modelAndView.addObject("currentPage", pageNum);
			
			modelAndView.setViewName("/page/accomm");
			return modelAndView;
		}

		// 숙소 상세
		@GetMapping("/page/detailAccomm/{a_id}")
		public String detailAccomm(Model model, @PathVariable("a_id") String a_id) {
			
			// 해당 숙소의 객실정보와 숙소id 상태유지
			model.addAttribute("detail1", adao.detailAcc1(a_id));
			model.addAttribute("detail2", adao.detailAcc2(a_id));
			model.addAttribute("a_id", a_id);
			return "/page/detailAccomm";
		}
	} 