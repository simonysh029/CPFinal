package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.AccommVO;
import com.example.demo.vo.EventVO;
import com.example.demo.vo.GuestroomVO;
import com.example.demo.vo.MemberVO;

public class DBAdminManager {
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
	
	//admin페이지에서 사용
		public static List<MemberVO> findAll() {
			List<MemberVO> list = null;
			SqlSession session = factory.openSession();
			list = session.selectList("member.findAll");
			return list;
		}

		public static List<AccommVO> findAllA() {
			List<AccommVO> list = null;
			SqlSession session = factory.openSession();
			list = session.selectList("accomm.findAllA");
			return list;
		}

		public static List<EventVO> findAllE() {
			List<EventVO> list = null;
			SqlSession session = factory.openSession();
			list = session.selectList("event.findAllE");
			return list;
		}

		public static List<GuestroomVO> findByaId(String a_id) {
			List<GuestroomVO> list = null;
			SqlSession session = factory.openSession();
			list = session.selectList("guestroom.findByaId", a_id);
			return list;
		}
		
	//관리자 회원	
		public static int updateMemberByAdmin(MemberVO m) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.insert("member.updateMemberByAdmin",m);
			session.commit();
			session.close();
			return re;
		}
		public static int insertAdmin(MemberVO m) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.insert("member.insertAdmin", m);
			session.commit();
			session.close();
			return re;
		}
		public static int deleteM(String cid) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.delete("member.deleteM", cid);
			session.commit();
			session.close();
			return re;
		}

	//관리자 이벤트
		public static int insertEvent(EventVO e) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.insert("event.insertEvent", e);
			session.commit();
			session.close();
			return re;
		}
		public static int getNextNo() {
			int getNextNo;
			SqlSession session = factory.openSession();
			getNextNo = session.selectOne("event.getNextNo");
			session.close();
			return getNextNo;
		}

		public static int updateEventByAdmin(EventVO e) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.insert("event.updateEventByAdmin", e);
			session.commit();
			session.close();
			return re;
		}
		public static int deleteE(int e_no) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.delete("event.deleteE", e_no);
			session.commit();
			session.close();
			return re;
		}
		
	//관리자 숙소
		public static int insertAccomm(AccommVO a) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.insert("accomm.insertAccomm", a);
			session.commit();
			session.close();
			return re;
		}
		public static AccommVO findByAid(String a_id) {
			AccommVO list = null;
			SqlSession session = factory.openSession();
			list = session.selectOne("accomm.findByAid", a_id);
			session.close();
			return list;
		}
		public static int updateAccommByAdmin(AccommVO a) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.update("accomm.updateAccommByAdmin", a);
			session.commit();
			session.close();
			return re;
		}

		public static int deleteA(String a_id) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.delete("accomm.deleteA", a_id);
			session.commit();
			session.close();
			return re;
		}
		
//관리자 객실
		public static GuestroomVO findByGid(String g_id) {
			GuestroomVO list = null;
			SqlSession session = factory.openSession();
			list = session.selectOne("guestroom.findByGid", g_id);
			session.close();
			return list;
		}
		public static int updateGuestroomByAdmin(GuestroomVO g) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.update("guestroom.updateGuestroomByAdmin", g);
			session.commit();
			session.close();
			return re;
		}
		public static int insertGuest(GuestroomVO g) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.insert("guestroom.insertGuest", g);
			System.out.println("re:"+re);
			session.commit();
			session.close();
			return re;
		}

		public static int deleteG(String g_id) {
			int re = -1;
			SqlSession session = factory.openSession();
			re = session.delete("guestroom.deleteG", g_id);
			session.commit();
			session.close();
			return re;
		}
}
