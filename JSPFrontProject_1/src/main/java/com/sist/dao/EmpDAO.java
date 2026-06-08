package com.sist.dao;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis
 *  =CRUD
 *  	<select> <update> <insert> <delete> 
 *  								|delete()
 *  						|insert()
 *  				| update()
 *  	| = List selectList()
 *  	| = VO selectOne()
 *  
 *  = Join / subQuery() 처리방법 => resultMap
 *  = 동적쿼리 *****
 *  	<if>
 *  	<forEach>
 *  	<where>
 *  	<choose>
 *  	<trim>				:다중검색
 *  = Annotation 사용법
 *  = MyBatis의 단점
 *  	- SQL문장을 하나만 처리할 수 있다
 *  	- 여러개 처리하는 방법
 *  	<select> 	: procedure 형식 => ex.회원탈퇴
 *  	BEGIN
 *  	{
 *  	}
 *  	END
 *  	{
 *  	}
 *  	</select>
 */
public class EmpDAO {
	private static SqlSessionFactory ssf;
	static{
		try {
			//XML 파싱
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//mapper.xml 도 함께 파싱됨
			ssf = new SqlSessionFactoryBuilder().build(reader);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//기능
	/**
	 * <select id="empListData" resultMap="empMap">
		SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,dname,loc
		FROM emp,dept
		WHERE emp.deptno=dept.deptno
		ORDER BY empno ASC
	</select>
	 */
	public static List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		SqlSession session = null;
		try {
			session=ssf.openSession();
			list = session.selectList("empListData");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//POOL안으로 반환
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
}
