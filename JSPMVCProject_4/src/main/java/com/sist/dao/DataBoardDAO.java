package com.sist.dao;

/***
 * db 연동
 * jdbc ===================> dbcp =======================> orm
 * 															데이터베이스 처리하는 라이브러리 집합
 * 															mybatis/JPA(개인)
 * 															= XML 이용하는 방식 : JSP 사이트
 * 															= Annotation : Spring-boot
 * 1) MyBatis(3버전) <= IBatis(2버전) : OpenSource 그룹
 * 		|구글에서 인수
 * 		SQL을 작성해서 MyBatis로 전송
 * 						| 자동으로 처리 (연결~전송~실행~결과값담기)
 * 
 * 			| => 설정필요 (XML파일)
 * 				1)sql문장
 * 				2)vo설정
 * 				3)?에 값채우기
 * 	
 * => 반복적인 구조를 없애줌
 * 	
 * 2) 동작 구조
 * 		=Config.xml => Connection => 한개만 설정
 * 		=mapper.xml => PrepareStatement / disConnection()
 * 			=>테이블당 1개 생성
 * 	1. Config.xml을 이용해서 => getConnection()/disConnection()
 * 	2. mapper읽어서 jdbc 이용해서 처리
 * 
 * 3) 장점
 * 	sql문장 직접 제어 => 개발자
 * 	성능 최적화 => 튜닝
 * 	복잡한 sql문장 제어
 * 
 * 4) 단점			
 * 	sql문장을 직접 제어 => jpa는 sql을 자동으로 만들어줌
 * 	db변경시 수정해야할게 많아짐
 * 	객체지향 orm이 약함
 * ----------------------------------------------------
 * sql문장은 동일 / 동적쿼리(다중 검색) / #{} / ${}
 * 										|''없이 처리 => table명/column명
 * 								  | ?에 값 채우기 => '' 
 * => 	resultType  : 결과값담기 (ResultSet)
 * 		parameterType : ?에 갑이 들어간다
 * 		  ?가 여러개인 경우 : vo
 * 		  vo에 없는 변수 여러개 : Map
 * 
 * 
 * SqlSessionFactory : SqlSession 생성 / XML 파싱
 * SqlSession : PrepareStatement/ResultSet 담당
 * 	
 */

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.DataBoardVO;

import java.io.*;

public class DataBoardDAO {
	//XML 파싱
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//classpath영역에 파일저장 => 경로명없이 파일명만 지정
			//src/main/java => spring에서도 자동인식
			ssf=new SqlSessionFactoryBuilder().build(reader);
			/**
			 * map.put("id","sql")
			 */
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//기능
	public static List<DataBoardVO> databoardListData(int start){
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		SqlSession session = null; //connection생성
		try {
			session=ssf.openSession();
			list=session.selectList("databoardListData", start);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close(); //반환(connection)
			}
		}
		return list;
	}
	public static int databoardTotalPage() {
		int total = 0;
		SqlSession session = null; //connection생성
		session=ssf.openSession();
		total=session.selectOne("databoardTotalPage");
		session.close(); //반환(connection)
		return total;
	}
	
	/**
	 * 	   <insert id="databoardInsert" parameterType="DataBoardVO">
	   	INSERT INTO mvcDataBoard values(
	   		mab_no_seq.nextval,
	   		#{name},
	   		#{subject},
	   		#{content},
	   		#{pwd},
	   		SYSDATE,
	   		0,
	   		#{filename},
	   		#{filesize},
	   	)
	   </insert>
	 */
	public static void databoardInsert(DataBoardVO vo) {
		//ssf.openSession(); ==> 자동 false
		SqlSession session = null;		
		try {
				session = ssf.openSession(true);
				session.insert("databoardInsert",vo);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
		session.close();
			}
		}
	}
	
	/**
	 * 	   <update id="hitIncrement" parameterType="int">
	   	UPDATE mvcDataBoard SET
	   	hit=hit+1
	   	WHERE NO=#{no}
	   </update>
	   <select id="databoardDetail" parameterType="int" resultType="DataBoardVO">
	   	SELECT no,subject,content,name,TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, filename, filesize
	   	FROM mvcDataBoard
	   	WHERE NO=#{no}
	   </select>
	 */
	public static DataBoardVO databoardDetail(int no) {
		SqlSession session = null;
		DataBoardVO vo =new DataBoardVO();
		try {
			session = ssf.openSession();
			session.update("hitIncrement",no);
			session.commit();
			//자동 형변환 => 제네릭을 이용한 형변환
			vo = session.selectOne("databoardDetail",no);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return vo;
	}
}
