package com.sist.bean;

import lombok.Data;

@Data
public class MemberBean {
	private String name;
	private int age;
	private String gender;
	private String address;
	private String phone;
	private String content;
	private boolean admin;
}
