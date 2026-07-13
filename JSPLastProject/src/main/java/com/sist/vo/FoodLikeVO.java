package com.sist.vo;

import lombok.Data;

/**
 * ID        NOT NULL NUMBER       
FNO                NUMBER       
MEMBER_ID          VARCHAR2(20) 
 */

@Data
public class FoodLikeVO {
	private int id,fno;
	private String member_id;
}
