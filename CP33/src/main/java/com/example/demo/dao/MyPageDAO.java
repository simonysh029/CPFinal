package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.ReserveVO;
import com.example.demo.vo.StayVO;

@Repository
public class MyPageDAO {	
	public List<ReserveVO> findReserve(String m_id) {
		return DBManager.findReserve(m_id);
	}
	public List<StayVO> findStay(String m_id) {
		return DBManager.findStay(m_id);
	}
	
	
}