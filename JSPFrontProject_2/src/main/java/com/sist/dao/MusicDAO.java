package com.sist.dao;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MusicDAO {
	public static SqlSessionFactory ssf;
	static {
		try {
			//xml 파싱
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <select id="musicListData" resultType="MusicVO">
		SELECT no,title,poster,singer,album,state,idcrement
		FROM genie_music
		WHERE cno=1
		ORDER BY no ASC
	</select>
	CRUD(JOIN/SUBQUERY)
	동적쿼리 : 다중조건 => 잡포털 / 검색 (필터)
	#{} : ?에 값채우기 => ''(o)
	${} : table, column명 => ''(x)
	< : 오류 $lt;
	 */
	public static List<MusicVO> musicListData() { 
		SqlSession session = null;
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			session = ssf.openSession();
			list = session.selectList("musicListData");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				session.close();		//POOL안에 반환 => 8/8
		}
		return list;
	}
}
