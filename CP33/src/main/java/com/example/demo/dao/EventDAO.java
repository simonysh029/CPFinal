package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.EventVO;
import com.example.demo.vo.MemberVO;

@Repository
public class EventDAO {

	public List<EventVO> listEvent() {
		return DBManager.listEvent();
	}

	public EventVO findByNo(int e_no) {
		return DBManager.findByNo(e_no);
	}

	public List<EventVO> mainEvent() {
		return DBManager.mainEvent();
	}

	public List<EventVO> findAllE() {
		return DBManager.listEvent();
	}

}
