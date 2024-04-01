package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.ReserveVO;

@Repository
public class MyPageDAO {
	public MemberVO findById(String m_id) {
		return DBManager.findById(m_id);
	}
	
	public List<ReserveVO> findReserve(String m_id) {
		return DBManager.findReserve(m_id);
	}
}