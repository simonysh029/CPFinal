package com.example.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GuestroomVO {
	private String g_id;
	private int g_price;
	private int g_person;
	private String g_exp;
	private String g_img;
	private MultipartFile uploadFileGuest;
	private String a_id;
} 