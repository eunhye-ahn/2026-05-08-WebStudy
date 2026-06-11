package com.sist.vo;
/**
 * NO      NOT NULL NUMBER       
FNO              NUMBER       
ID               VARCHAR2(20) 
NAME    NOT NULL VARCHAR2(51) 
MSG     NOT NULL CLOB         
REGDATE          DATE  
 */

import java.util.Date;

import lombok.Data;

//댓글 한개에 대한 정보
@Data
public class replyVO {
	private int no,fno;
	private String id,name,msg,dbday;
	private Date regdate;
}
