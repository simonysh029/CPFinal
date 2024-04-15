package com.example.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EventVO {
	private int e_no;
	private String e_main;
	private String e_all;
	private MultipartFile uploadFileAll;
	private String e_each;
	private MultipartFile uploadFileEach;
}
