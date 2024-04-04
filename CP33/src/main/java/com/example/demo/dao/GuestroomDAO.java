package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.GuestroomVO;

@Repository
public class GuestroomDAO {

	public List<GuestroomVO> findByaId(String a_id) {
		return DBManager.findByaId(a_id);
	}
}
