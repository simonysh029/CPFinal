package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.AccListVO;

@Repository
public class AccommDAO {

	public List<AccListVO> listAcc(String a_div){
		return DBManager.listAcc(a_div);
	}
}
