package com.sist.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

public class FoodDAO {
	public static SqlSessionFactory ssf;
	static {
		try {
			//XML파싱
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * <select id="foodListData" resultType="FoodVO" parameterType="int">
		SELECT no,poster,name
		FROM food
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	
		<select id="foodTotalPage" resultType="int" >
		SELECT CEIL(COUNT(*)/12.0)
		FROM food
		</select>
		
		<select id="foodDetailData" resultType="foodVO" parameterType="int">
		SELECT *
		FROM food
		WHERE no= #{no}
	</select>
	 */
	//ajax : json / jsp : request
	public static List<FoodVO> foodListData(int start) {
		//connection을 이용해서 ps/rs
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData", start);
		session.close();
		return list;
	}
	public static int foodTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
	public static FoodVO foodDetailData(int no) {
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodDetailData",no);
		session.close();
		return vo;
	} 
}
