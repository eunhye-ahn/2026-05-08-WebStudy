package com.sist.dao;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
import com.sist.commons.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	/*
	 *<select id="foodListData" resultType="FoodVO" parameterType="int">
			SELECT no,name,poster,address,phone,likecount,replycount
			FROM food
			ORDER BY no ASC
			OFFSET #{start} ROWS FETCH 12 ROWS ONLY
		</select>
		<select id="foodTotalPage">
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
	/**
	 * <update id="foodHitIncrement" parameterType="int">
			UPDATE food SET
			hit = hit+1
			WHERE no=#{no}
		</update>
		<select id="foodDetailData" parameterType="int" resultType="FoodVO">
			SELECT *
			FROM food
			WHERE no=#{no}
		</select>
		rownum??
		<select id="foodRearData" resultType="FoodVO" parameterType="string">
			SELECT no,name,poster,address,rownum
			FROM food
			WHERE address LIKE '%'||#{address}||'%'
			AND rownum&lt;=7
		</select>
	 */
	public static FoodVO foodDetailData(int no) {
		SqlSession session = ssf.openSession(true);
		session.update("foodHitIncrement",no);
		FoodVO vo = session.selectOne("foodDetailData",no);
		session.close();
		return vo;
	}
	public static List<FoodVO> foodRearData(String address)
	   {
		   SqlSession session=ssf.openSession();
		   List<FoodVO> list=session.selectList("foodRearData",address);
		   session.close();
		   return list;
	   }
	/**
	 * <select id="foodCategoryData" resultType="FoodVO" parameterType="string">
	  		SELECT no,name,poster
	  		FROM food
	  		WHERE type LIKE '%'||#{type}||'%'
	  		ORDER BY no ASC
	  	</select>
	 */
	public static List<FoodVO> foodCategoryData(String type){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodCategoryData",type);
		session.close();
		return list;
	}
	
}
