package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.AccListVO;
import com.example.demo.vo.AccommVO;
import com.example.demo.vo.EventVO;
import com.example.demo.vo.MemberVO;

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
	
	public static List<AccListVO> listAcc(String a_div, int g_person, String keyword) {
		List<AccListVO> list = null;
		SqlSession session = factory.openSession();
		
		Map<String, Object> map = new HashMap<>();
		map.put("a_div", a_div);
		map.put("g_person", g_person);
		map.put("keyword", keyword);
		
		list = session.selectList("accomm.list", map);
		session.close();
		return list;
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
	
	
	

}
