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
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.ReserveVO;

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
	
	// 숙소 검색, 조회 목록
	public static List<ListAccommVO> listAcc(String a_div, int g_person, String keyword) {
		List<ListAccommVO> list = null;
		SqlSession session = factory.openSession();
		
		Map<String, Object> map = new HashMap<>();
		map.put("a_div", a_div);
		map.put("g_person", g_person);
		map.put("keyword", keyword);
		
		list = session.selectList("accomm.list", map);
		session.close();
		return list;
	}
	
	// 숙소 상세 1
	public static DetailAccommVO detailAcc1(String a_id) {
		DetailAccommVO acc = null;
		SqlSession session = factory.openSession();
		acc = session.selectOne("accomm.detail1",a_id);
		return acc;
	}

	// 숙소 상세 2
	public static DetailAccommVO detailAcc2(String a_id) {
		DetailAccommVO acc = null;
		SqlSession session = factory.openSession();
		acc = session.selectOne("accomm.detail2",a_id);
		return acc;
	}
	
//	----- member -----
	
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
	public static MemberVO findById(String m_id) {
		MemberVO m = null;
		SqlSession session = factory.openSession();
		m = session.selectOne("member.findById",m_id);
		session.close();
		return m;
	}

// ----- event -----
	
	//listEvent
	public static List<EventVO> listEvent() {
		List<EventVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("event.listEvent");
		return list;
	}

	public static EventVO findByNo(int e_no) {
		EventVO e = null;
		SqlSession session = factory.openSession();
		e = session.selectOne("event.findByNo",e_no);
		return e;
	}

	// 메인페이지에 출력하는 리스트
	public static List<EventVO> mainEvent() {
		List<EventVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("event.mainEvent");
		return list;
	}

	// ----- myPage -----
	public static List<ReserveVO> findReserve(String m_id) {
		List<ReserveVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectOne("mypage.findReserve",m_id);
		return list;
	}

}
