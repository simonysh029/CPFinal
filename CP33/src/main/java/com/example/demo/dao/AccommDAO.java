package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.ListAccommVO;
import com.example.demo.vo.AccommVO;
import com.example.demo.vo.DetailAccommVO;

@Repository
public class AccommDAO {

	public List<ListAccommVO> listAcc(String a_div, int g_person, String keyword){
		return DBManager.listAcc(a_div, g_person, keyword);
	}

	public List<AccommVO> popH() {
		return DBManager.popH();
	}

	public List<AccommVO> popP() {
		return DBManager.popP();
	}

	public List<AccommVO> popM() {
		return DBManager.popM();
	}

	public List<AccommVO> popF() {
		return DBManager.popF();
	}

	public List<AccommVO> popB() {
		return DBManager.popB();
	}
	
	public DetailAccommVO detailAcc1(String a_id) {
		return DBManager.detailAcc1(a_id);
	}
	
	public DetailAccommVO detailAcc2(String a_id) {
		return DBManager.detailAcc2(a_id);
	}
}
