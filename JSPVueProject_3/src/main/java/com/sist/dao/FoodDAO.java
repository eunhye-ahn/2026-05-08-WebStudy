package com.sist.dao;

import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;

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
	/*
	 * <select id="foodListData" resultType="FoodVO" parameterType="int">
			SELECT no,name,poster,address
			FROM food
			ORDER BY no ASC
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
		<select id="foodTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/12.0) FROM food
		</select>
	 */
	public static List<FoodVO> foodListData(int start){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodListData",start);
		session.close();
		return list;
	}
	public static int foodTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
	/*
	 * <select id="foodDetailData" resultType="FoodVO" parameterType="int">
			SELECT *
			FROM food
			WHERE no=#{no}
		</select>
	 */
	public static FoodVO foodDetailData(int no) {
		SqlSession session = ssf.openSession();
		FoodVO vo = session.selectOne("foodDetailData",no);
		session.close();
		return vo;
	}
	/*
	 * <select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
			SELECT *
			FROM food
			WHERE #{column} LIKE '%'||#{ss}||'%'
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
	 */
	public static List<FoodVO> foodFindData(Map map){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData",map);
		session.close();
		return list;
	}
	/*
	 * <select id="foodFindTotalPage" resultType="int" parameterType="hashmap">
		SELECT CEIL(COUNT(*)/12.0) FROM food
		WHERE #{column} LIKE '%'||#{ss}||'%'
	</select>
	 * */
	public static int foodFindTotalPage(Map map) {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("foodFindTotalPage",map);
		session.close();
		return total;
	}
}
