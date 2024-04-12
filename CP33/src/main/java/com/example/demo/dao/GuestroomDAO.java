package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBAdminManager;
import com.example.demo.db.DBManager;
import com.example.demo.vo.GuestroomVO;

@Repository
public class GuestroomDAO {

	public List<GuestroomVO> findByaId(String a_id) {
		return DBAdminManager.findByaId(a_id);
	}
	public GuestroomVO findByGid(String g_id) {
		return DBAdminManager.findByGid(g_id);
	}
	public int updateGuestroomByAdmin(GuestroomVO g) {
		return DBAdminManager.updateGuestroomByAdmin(g);
	}
	public int insertGuest(GuestroomVO g) {	
		return DBAdminManager.insertGuest(g); 
	}
	public int deleteG(String g_id) {
		return DBAdminManager.deleteG(g_id);
		
	}
}
