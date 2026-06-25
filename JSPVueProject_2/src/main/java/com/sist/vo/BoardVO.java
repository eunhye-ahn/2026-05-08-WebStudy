package com.sist.vo;

import java.util.Date;

import lombok.Data;

/**
 * NO       NOT NULL NUMBER         
NAME     NOT NULL VARCHAR2(51)   
SUBJECT  NOT NULL VARCHAR2(2000) 
CONTENT  NOT NULL CLOB           
PWD      NOT NULL VARCHAR2(10)   
REGDATE           DATE           
HIT               NUMBER         
FILENAME          VARCHAR2(260)  
FILESIZE          NUMBER     
 */
@Data
public class BoardVO {
	private int no,hit,filesize;
	private String name,subject,content,pwd,dbday,filename;
	private Date regdate;
}
