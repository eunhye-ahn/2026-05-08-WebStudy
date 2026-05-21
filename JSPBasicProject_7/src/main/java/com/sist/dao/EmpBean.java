package com.sist.dao;

import java.util.*;

import lombok.Data;

@Data
public class EmpBean {
	private int empno,deptno,sal,mgr;
	private String ename,job,dbday;
	private Date hiredate;
}