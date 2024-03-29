package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.AccListVO;
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
	
	public static List<AccListVO> listAcc(String a_div) {
		List<AccListVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("accomm.list", a_div);
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

	
}
