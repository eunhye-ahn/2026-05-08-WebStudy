package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.*;
import java.util.*;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			ssf=CreateSqlSessionFactory.getSsf();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <select id="empFind" parameterType="EmpVO" resultType="EmpVO">
	 		SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') AS dbday, sal, deptno
	 		FROM emp
	 		<where>				<!-- 자동으로 WHERE 첫번째 OR/AND 제거 -->
	 			<if test="empno!=null">
	 				AND empno=#{empno}
	 			</if>
	 			<if test="ename!=null and ename!=''">
	 				AND ename LIKE '%'||#{ename}||'%'
	 			</if>
	 			<if test="deptno!=null">
	 				AND deptno=#{deptno}
	 			</if>
	 		</where>
	 	</select>
	 	 <select id="empFind2" resultType="EmpVO" parameterType="string">
	 		<bind 
	 			name="keyword"
	 			value="'%'+ename+'%'"
	 		/>
	 		SELECT empno, ename, job, hiredate, sal, deptno
	 		FROM emp
	 		WHERE ename LIKE #{keyword}
	 	</select>
	 	<select id="empFind3" resultType="EmpVO" parameterType="string">
	 		SELECT empno, ename, job, hiredate, sal, deptno
	 		FROM emp
	 		WHERE
	 		<choose>
	 			<when test="job=='MANAGER'">
	 				sal>=3000
	 			</when>
	 			<when test="job=='SALESMAN'">
	 				sal>=1500
	 			</when>
	 			<otherwise>
	 				sal>=1000
	 			</otherwise>
	 		</choose>
	 	</select>
	 */
	public static List<EmpVO> empFind(EmpVO vo){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empFind",vo);
		session.close();
		return list;
	}
	public static List<EmpVO> empFind2(String ename){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empFind2",ename);
		session.close();
		return list;
	}
	public static List<EmpVO> empFind3(String job){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empFind3",job);
		session.close();
		return list;
	}
	/**
	 * <select id="empFind4" resultType="EmpVO" parameterType="hashmap">
	 		SELECT empno, ename, job,  TO_CHAR(hiredate,'YYYY-MM-DD') AS dbday, sal, deptno
	 		FROM emp
	 		WHERE empno IN
	 		<foreach collection="data" item="emp" separator=","
	 			open="(" close=")"
	 		>
	 			#{emp}
	 		</foreach>
	 	</select>
	 	<select id="empGetData" resultType="EmpVO">
	 		SELECT empno, ename
	 		FROM emp
	 		ORDER BY empno ASC
	 	</select>
	 */
	public static List<EmpVO> empFind4(Map map){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empFind4",map);
		session.close();
		return list;
	}
	public static List<EmpVO> empGetData(){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empGetData");
		session.close();
		return list;
	}
	/**
	 * <select id="empFind5" parameterType="EmpVO" resultType="EmpVO">
	 		SELECT empno, ename, job,  TO_CHAR(hiredate,'YYYY-MM-DD') AS dbday, sal, deptno
	 		FROM emp
	 		<trim prefix="WHERE" prefixOverrides="AND/OR">
	 			<if test="ename!=null and enmae!=''">
	 				AND ename LIKE '%'||#{ename}||'%'
	 			</if>
	 			<if test="job!=null and job!=''">
	 				AND job LIKE '%'||#{job}||'%'
	 			</if>
	 		</trim>
	 	</select>
	 */
	public static List<EmpVO> empFind5(EmpVO vo){
		SqlSession session = ssf.openSession();
		List<EmpVO> list = session.selectList("empFind5",vo);
		session.close();
		return list;
	}
}
