package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class OrdersVO {
	private Date r_start;
	private Date r_end;
	private int r_person;
	private String g_id;
	private String a_id;
}
