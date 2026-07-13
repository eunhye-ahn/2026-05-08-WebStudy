package com.sist.vo;

import java.util.Date;

import lombok.Data;

/**
 * NO      NOT NULL NUMBER         
TYPE             NUMBER         
NAME             VARCHAR2(20)   
SUBJECT NOT NULL VARCHAR2(2000) 
CONTENT NOT NULL CLOB           
REGDATE          DATE           
HIT              NUMBER 
 */

@Data
public class NoticeVO {
	private int no,hit,type;
	private String name,subject,content,dbday;
	private Date regdate;
}
