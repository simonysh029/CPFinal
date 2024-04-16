package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBAdminManager;
import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@Repository
public class MemberDAO {

	public String idCheck(String m_id) {
		return DBManager.idCheck(m_id);
	}
	
	public String idFind(MemberVO m) {
		return DBManager.idFind(m);
	}
	
	public String pwFind(MemberVO m) {
		return DBManager.pwFind(m);
	}	
	
	public MemberVO findById(String username) {
		return DBManager.findById(username);
	}
	
	public String findRole(String id) {
		return DBManager.findRole(id);
	}

	public int insert(MemberVO m) {
		return DBManager.insertMember(m);
	}
	
	//마이페이지에서 넘어간 회원정보수정
	public int updateMember(MemberVO m) {
		return DBManager.updateMember(m);
	}

	//관리자 화면에서 회원등록
	public int insertAdmin(MemberVO m) {
		return DBAdminManager.insertAdmin(m);
	}
	//관리자 화면에서 넘어간 회원정보수정
	public int updateMemberByAdmin(MemberVO m) {
		return DBAdminManager.updateMemberByAdmin(m);
	}

	public List<MemberVO> findAll() {
		return DBAdminManager.findAll();
	}
		
	public int deleteM(String cid) {
		return DBAdminManager.deleteM(cid);		
	}


}