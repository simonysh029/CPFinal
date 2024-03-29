package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.EventVO;

@Repository
public class EventDAO {

	public List<EventVO> listEvent() {
		return DBManager.listEvent();
	}

	public EventVO findByNo(int e_no) {
		return DBManager.findByNo(e_no);
	}

}
