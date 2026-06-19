package com.sist.vo;
/**
NO       NOT NULL NUMBER         
NAME     NOT NULL VARCHAR2(51)   
SUBJECT  NOT NULL VARCHAR2(2000) 
CONTENT  NOT NULL CLOB           
PWD      NOT NULL VARCHAR2(10)   
REGDATE           DATE           
HIT               NUMBER         
FILENAME          VARCHAR2(260)  
FILESIZE          NUMBER       
 */

import java.util.Date;

import lombok.Data;

/*
 * 벤치마킹 => 화면UI (페이지분석) => 화면캡처
 * 요구사항 => 기능
 * 벤치마킹 화면에서 프로젝트에 필요한 데이터 추출
 * 데이터베이스 설정
 * 데이터 수집 (크롤링) => INSERT
 * 메인 화면 제작 => 공통 => GIT
 * 회원가입 / 로그인
 * 역할 분담
 * 종료 => 테스트 (Junit) : 단위테스트
 * 배포 => AWS
 * 
 * CRUD정리 파일업로드, 파일다운로드 => 갤러리 게시판/후기게시판
 */
@Data
public class DataBoardVO {
	private int no,hit,filesize;
	private String name,subject,content,pwd,dbday,filename;
	private Date regdate;
}
