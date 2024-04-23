package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.ReserveVO;
import com.example.demo.vo.StayVO;

@Repository
public class MyPageDAO {	
	public List<ReserveVO> findReserve(String username) {
		return DBManager.findReserve(username);
	}
	public List<StayVO> findStay(String username) {
		return DBManager.findStay(username);
	}

} 