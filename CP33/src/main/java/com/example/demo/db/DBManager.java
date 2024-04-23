package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.ListAccommVO;
import com.example.demo.vo.AccommVO;
import com.example.demo.vo.DetailAccommVO;
import com.example.demo.vo.EventVO;
import com.example.demo.vo.GuestroomVO;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.PaymentVO;
import com.example.demo.vo.ReserveVO;
import com.example.demo.vo.StayVO;

public class DBManager {
	private static SqlSessionFactory factory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}  
	
//	----- accomm -----
	
	// 추천 숙소
	public static List<AccommVO> popH() {
		List<AccommVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("accomm.popH");
		session.close();
		return list;
	}
	
	public static List<AccommVO> popP() {
		List<AccommVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("accomm.popP");
		session.close();
		return list;
	}

	public static List<AccommVO> popM() {
		List<AccommVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("accomm.popM");
		session.close();
		return list;
	}

	public static List<AccommVO> popF() {
		List<AccommVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("accomm.popF");
		session.close();
		return list;
	}

	public static List<AccommVO> popB() {
		List<AccommVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("accomm.popB");
		session.close();
		return list;
	}
	
	// 전체 레코드 수
	public static int getTotalRecord(String a_div, int g_person, String keyword) {
		int re = 0;
		SqlSession session = factory.openSession();
		
		Map<String, Object> map = new HashMap<>();
		map.put("a_div", a_div);
		map.put("g_person", g_person);
		map.put("keyword", keyword);
		
		re = session.selectOne("accomm.totalRecord",map);
		session.close();
		return re;
	}
	
	// 숙소 검색, 조회 목록
	public static List<ListAccommVO> listAcc(String a_div, int g_person, String keyword, int offset, int limit) {
		List<ListAccommVO> list = null;
		SqlSession session = factory.openSession();
		
		Map<String, Object> map = new HashMap<>();
		map.put("a_div", a_div);
		map.put("g_person", g_person);
		map.put("keyword", keyword);
		map.put("offset", offset);
		map.put("limit", limit);
		
		list = session.selectList("accomm.list", map);
		session.close();
		return list;
	}
	
	// 숙소 상세 1
	public static DetailAccommVO detailAcc1(String a_id) {
		DetailAccommVO acc = null;
		SqlSession session = factory.openSession();
		acc = session.selectOne("accomm.detail1",a_id);
		session.close();
		return acc;
	}

	// 숙소 상세 2
	public static DetailAccommVO detailAcc2(String a_id) {
		DetailAccommVO acc = null;
		SqlSession session = factory.openSession();
		acc = session.selectOne("accomm.detail2",a_id);
		session.close();
		return acc;
	}
	
//	----- member -----
	
	public static String idCheck(String m_id) {
		String id = "";
		SqlSession session = factory.openSession();
		id = session.selectOne("member.idCheck", m_id);
		session.close();
		return id;
	}
	
	public static String idFind(MemberVO m) {
		String id = "";
		SqlSession session = factory.openSession();
		id = session.selectOne("member.idFind", m);
		session.close();
		return id;
	}

	public static String pwFind(MemberVO m) {
		String pw = "";
		SqlSession session = factory.openSession();
		pw = session.selectOne("member.pwFind", m);
		session.close();
		return pw;
	}
	
	// 회원가입
	public static int insertMember(MemberVO m) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("member.insert",m);
		session.commit();
		session.close();
		return re;
	}
	
	// 회원조회 By Id
	public static MemberVO findById(String username) {
		MemberVO m = null;
		SqlSession session = factory.openSession();
		m = session.selectOne("member.findById",username);
		session.close();
		return m;
	}

	//role 뽑아오기
	public static String findRole(String id) {
		String role = null;
		SqlSession session = factory.openSession();
		role = session.selectOne("member.findRole", id);
		session.close();
		return role;
	}
	
// ----- event -----
	
	//listEvent
	public static List<EventVO> listEvent() {
		List<EventVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("event.listEvent");
		session.close();
		return list;
	}

	public static EventVO findByNo(int e_no) {
		EventVO e = null;
		SqlSession session = factory.openSession();
		e = session.selectOne("event.findByNo",e_no);
		session.close();
		return e;
	}

	// 메인페이지에 출력하는 리스트
	public static List<EventVO> mainEvent() {
		List<EventVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("event.mainEvent");
		session.close();
		return list;
	}

	// ----- myPage -----
	public static List<ReserveVO> findReserve(String username) {
		List<ReserveVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("mypage.findReserve", username);
		session.close();
		return list;
	}
	public static List<StayVO> findStay(String username) {
		List<StayVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("mypage.findStay", username);
		session.close();
		return list;
	}
	public static int updateMember(MemberVO m) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("member.updateMember",m);
		session.commit();
		session.close();
		return re;
	}

	// ----- Payement -----
	public static int findPrice(String g_id) {
		SqlSession session = factory.openSession();
		int g_price;
		g_price = session.selectOne("guestroom.findPrice", g_id);
		session.close();
		return g_price;
	}
	public static int getNextPno() {
		int re;
		SqlSession session = factory.openSession();
		re = session.selectOne("event.getNextNo");
		session.close();
		return re;
	}
	public static int insertPayment(PaymentVO p) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("payment.insertPayment",p);
		session.commit();
		session.close();
		return re;
	}
	public static int insertReserve(ReserveVO r) {
		int re=-1;
		SqlSession session = factory.openSession();
		re = session.insert("reserve.insertReserve",r);
		session.commit();
		session.close();
		return re;
	}
}
