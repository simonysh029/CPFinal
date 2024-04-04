package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class MyStayVO {
	private String a_name;
	private String a_addr;	
	private Date s_start;
	private Date s_end;
	private String m_id;
}