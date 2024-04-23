package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class PaymentVO {	
	private int p_no;
	private String p_card;	
	private Date p_date;
	private int p_amount;
	private int p_person;
	private String g_id;
	private String p_id;
	private String m_id;
}

 

