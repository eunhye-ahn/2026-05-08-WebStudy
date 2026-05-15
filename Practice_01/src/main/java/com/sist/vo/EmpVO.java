package com.sist.vo;

import java.sql.Date;

import lombok.Data;

/**
 * EMPNO    NOT NULL NUMBER       
ENAME    NOT NULL VARCHAR2(50) 
JOB      NOT NULL VARCHAR2(50) 
HIREDATE NOT NULL DATE         
SAL      NOT NULL NUMBER(10,2) 
DEPTNO   NOT NULL NUMBER   
 */
@Data
public class EmpVO {
	private int no, sal, deptno;
	private String ename,job,dbday;
	private Date hiredate;
}
