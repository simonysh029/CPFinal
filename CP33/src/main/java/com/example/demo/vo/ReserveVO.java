package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class ReserveVO {
	private String a_name;
	private String a_addr;
	private Date r_date;
	private Date r_start;
	private Date r_end;
	private String m_id;
}