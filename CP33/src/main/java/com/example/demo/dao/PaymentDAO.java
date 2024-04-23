package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.DetailAccommVO;
import com.example.demo.vo.PaymentVO;

@Repository
public class PaymentDAO {

	public int findPrice(String g_id) {
		return DBManager.findPrice(g_id);
	}
	public int insertPayment(PaymentVO p) {
		return DBManager.insertPayment(p);
	}
	public int getNextPno() {
		return DBManager.getNextPno();
	}
}
