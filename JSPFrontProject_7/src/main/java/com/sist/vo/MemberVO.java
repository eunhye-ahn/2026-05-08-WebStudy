package com.sist.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private String id,pwd,sex,post,addr1,addr2,phone,content,isAdmin,dbday,msg,name;
	private Date regdate;
}