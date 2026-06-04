package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSessionFactory.getSsf();
	}
	
	//기능
	//1.목록
	public static List<FoodVO> foodListData(int start){
		List<FoodVO> list = new ArrayList<FoodVO>();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			list = session.selectList("foodListData", start);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}
	public static int foodTotalPage() {
		int total = 0;
		SqlSession session = null;
		try {
			session = ssf.openSession();
			total = session.selectOne("foodTotalPage");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return total;
	}
	public static FoodVO foodDetailData(int no){
		FoodVO vo = new FoodVO();
		SqlSession session = null;
		try {
			session = ssf.openSession();
			vo = session.selectOne("foodDetailData", no);
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
