package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/**
	 * <select id="foodListData" parameterType="int" resultType="FoodVO">
			SELECT no,name,poster,address
			FROM food
			ORDER BY no ASC
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS NOLY
		</select>
		<select id="foodTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/12.0) FROM food
		</select>
		<select id="foodDetailData" parameterType="int" resultType="FoodVO">
			SELECT *
			FROM food
			WHERE no=#{no}
		</select>
	 */
	public static List<FoodVO> foodListData(int start){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData",start);
		session.close();
		return list;
	}
	public static int foodTotalPage(){
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
