package com.sist.vo;

import java.util.*;

import lombok.Data;

/**
 * 	MyBatis
 * 1.CURD => <select> <insert> <update> <delete>
 * 			resultType/parameterType
 * 2.JOIN
 * 		resultMap : dvo.dname => getDvo().setDname()
 * 3.동적쿼리 = JSTL과 문법 유사
 * 	<if test="조건">
 * 	<choose>
 * 		<when test=""></when>
 * 		<otherwise></otherwise>
 * 	</choose> 
 * 	<forEach var="" itmes=""> : IN
 * 	<where>		<set>	<trim>	<bind>
 */
@Data
public class EmpVO {
	private int empno, deptno;
	private String ename,job,dbday,sal;
	private Date hiredate;
}
