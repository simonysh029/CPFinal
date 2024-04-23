package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.ReserveVO;

@Repository
public class ReserveDAO {

	public int insertReserve(ReserveVO r) {
		return DBManager.insertReserve(r);
	}

	public int getNextRno() {
		return DBManager.getNextRno();
	}

}
 