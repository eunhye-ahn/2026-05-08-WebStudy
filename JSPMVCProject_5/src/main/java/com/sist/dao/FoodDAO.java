package com.sist.dao;

/**
 * MyBatis
 * => Config.xml : Connection
 * => mapper.xml : SQL문장 => PrepareStatement/ResultSet
 * => VO : 한개의 정보저장하는 장소
 * => DAO : XML + 자바 연결
 * => 분업
 */
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;

public class FoodDAO {
	//XML의 데이터를 저장하는 클래스
	private static SqlSessionFactory ssf;
	//XML 파싱
	static {	//초기화 블록 => 자동호출 / 상속예외
		try {
			//XML읽기 => 한번에 모든 XML을 포함하고 있는 XML이 존재 : Config.xml
			Reader reader = Resources.getResourceAsReader("Config.xml");
			//파싱된 내용 저장
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//목록
	public static List<FoodVO> foodListData(int start){
		List<FoodVO> list = new ArrayList<FoodVO>();
		SqlSession session = null;
		try {
			//session 생성 : preparestatement / resultset
			session = ssf.openSession();
			list = session.selectList("foodListData",start);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return list;
	}
	//총페이지
	public static int foodTotalPage() {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("foodTotalPage");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return total;
	}
	
	//상세페이지
	public static FoodVO foodDetailData(int no) {
		SqlSession session = null;
		FoodVO vo = new FoodVO();
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodDetailData", no);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return vo;
	}
}
