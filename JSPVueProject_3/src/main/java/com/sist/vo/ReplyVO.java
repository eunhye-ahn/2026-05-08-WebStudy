package com.sist.vo;

import java.util.Date;

import lombok.Data;

/**
 * NO                                        NOT NULL NUMBER
 FNO                                                NUMBER
 ID                                                 VARCHAR2(20)
 NAME                                      NOT NULL VARCHAR2(51)
 MSG                                       NOT NULL CLOB
 REGDATE                                            DATE
 */
@Data
public class ReplyVO {
	private int no,cno,rno;
	private String id,name,msg,dbday,uMsg;
	private Date regdate;
	private boolean show;
}
