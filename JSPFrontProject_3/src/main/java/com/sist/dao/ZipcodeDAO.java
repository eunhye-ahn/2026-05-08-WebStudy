package com.sist.dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ZipcodeDAO {
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
	
	public static List<ZipcodeVO> postFind(String dong){
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("postFind",dong);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}
	
	public static int postFindCount(String dong) {
		int count = 0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count = session.selectOne("postFindCount", dong);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return count;
	}
}
