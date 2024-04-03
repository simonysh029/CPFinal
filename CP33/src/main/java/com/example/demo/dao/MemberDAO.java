package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@Repository
public class MemberDAO {
	
	public MemberVO findById(String username) {
		return DBManager.findById(username);
	}
	public int insert(MemberVO m) {
		return DBManager.insertMember(m);
	}
	
	//마이페이지에서 넘어간 회원정보수정
	public int updateMember(MemberVO m) {
		return DBManager.updateMember(m);
	}

}