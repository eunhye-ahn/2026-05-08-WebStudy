package com.sist.vo;

import lombok.Data;

/**
 * 패키지
 * 	com.sist.commons	: 전체클래스 사용하는 공통 기반
 * 							=> DAO : SqlSessionFactory
 * 							=> Footer에서 사용
 * 	com.sist.dao		: 데이터베이스 연동
 * 	com.sist.model		: 요청 처리 결과 전송 (브라우저)
 * 	com.sist.mapper		: 요청에 해당하는 SQL문장 작성
 * 	com.sist.vo			: 데이터를 모아서 한번에 전송
 * 	com.sist.manager	: 실시간 채팅 / 이메일 전송 / OpenAPI(뉴스,인기검색어)
 * ------------------------------------------------------------------
 * View
 * 	Jquery / AJAX / JavaScript 
 */
/**
 * NO         NOT NULL NUMBER        
POSTER              VARCHAR2(260) 
PRICE               VARCHAR2(100) 
CNO                 NUMBER        
NAME       NOT NULL VARCHAR2(100) 
TYPE       NOT NULL VARCHAR2(200) 
PHONE      NOT NULL VARCHAR2(20)  
ADDRESS    NOT NULL VARCHAR2(500) 
SCORE               NUMBER(2,1)   
THEME      NOT NULL CLOB          
TIME                VARCHAR2(50)  
RESERVE             VARCHAR2(100) 
PARKING             VARCHAR2(100) 
CONTENT    NOT NULL CLOB          
IMAGES              CLOB          
LIKECOUNT           NUMBER        
REPLYCOUNT          NUMBER        
JJIMCOUNT           NUMBER        
HIT                 NUMBER        
 */
@Data
public class FoodVO {
	private int no,likecount,replycount,jjimcount,hit;
	private String poster,price,name,type,phone,address,theme,time,reserve,parking,content,images;
	private Double score;
}
