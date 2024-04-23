package com.example.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AccommVO {
	private String a_id;
	private String a_div;
	private String a_name;
	private String a_addr;
	private double a_grade;
	private String a_lat;
	private String a_lng;
	private String a_exp;
	private String a_img;
	private MultipartFile uploadFileAcc;
} 