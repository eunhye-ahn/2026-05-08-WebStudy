package com.sist.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVO {
	 private int no,fno;
	   private String id,name,msg,dbday;
	   private Date regdate;
	
}
