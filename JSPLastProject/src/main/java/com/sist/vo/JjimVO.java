package com.sist.vo;
/**
 * JNO     NOT NULL NUMBER       
FNO              NUMBER       
ID               VARCHAR2(20) 
REGDATE          DATE 
 */

import java.util.Date;

import lombok.Data;

@Data
public class JjimVO {
	private int jno,fno;
	private String id,dbday;
	private Date regdate;
	//join
	private FoodVO fvo = new FoodVO();
}
