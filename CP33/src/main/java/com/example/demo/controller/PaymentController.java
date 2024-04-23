package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.AccommDAO;
import com.example.demo.dao.GuestroomDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.dao.PaymentDAO;
import com.example.demo.dao.ReserveDAO;
import com.example.demo.vo.AccommVO;
import com.example.demo.vo.DetailAccommVO;
import com.example.demo.vo.GuestroomVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.PaymentVO;
import com.example.demo.vo.ReserveVO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
public class PaymentController {
	@Autowired
	private PaymentDAO pdao;
	@Autowired
	private MemberDAO mdao;
	@Autowired
	private ReserveDAO rdao;
	@GetMapping("/page/orders")
	public void payForm(OrdersVO ovo, Model model, HttpSession session) {
		String g_id = ovo.getG_id();
		int g_price = pdao.findPrice(g_id);
		String m_id = (String) session.getAttribute("id");
		model.addAttribute("g_price", g_price);
		model.addAttribute("omList", mdao.findById(m_id));
		model.addAttribute("oList", ovo);
		model.addAttribute("nextPno", pdao.getNextPno());
	}
	@PostMapping("/page/payment")
	@ResponseBody
	public String payment(@RequestBody PaymentVO pvo) {
//		int p_no = pdao.getNextPno();
//		pvo.setP_no(p_no);
//		System.out.println(pvo);
//		int re = pdao.insertPayment(pvo);
//		return re;
		
		String view = null;
		try {
			pdao.insertPayment(pvo);
			view = "/page/payOk";
			return view; 
		} catch (Exception e) {
			e.printStackTrace();
			view="/page/payError";
			return view;
		}
	}
	
    @PostMapping("/page/payment")
    @ResponseBody
    public String handlePayment(@RequestBody PaymentVO paymentVO) {
        try {
            // 결제 정보를 데이터베이스에 저장합니다.
            paymentDAO.insertPayment(paymentVO);
            return "Payment processed successfully"; // 성공적으로 처리되었음을 클라이언트에 반환합니다.
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to process payment"; // 처리 중 오류가 발생했음을 클라이언트에 반환합니다.
        }
    }
	
	@GetMapping("/page/reserve")
	public int reserve(ReserveVO rvo) {
		int re = rdao.insertReserve(rvo);
		System.out.println(re);
		return re;
	}
	@GetMapping("/page/payOK")
	public void payOK() {
	}	
	@GetMapping("/page/payError")
	public void payError() {
	}
}
