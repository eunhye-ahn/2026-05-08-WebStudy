package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
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
	
	/**
	 * <select id="foodDetailData" parameterType="int" resultType="FoodVO">
		SELECT no,poster,name,type,phone,address,score,theme,reserve,parking,content,images,likecount,replycount,jjimcount,hit
		FROM food2
		WHERE no=#{no}
	</select>
	 */
	public static FoodVO foodDetailData(int no) {
		SqlSession session = ssf.openSession();
		session.update("foodHitIncrement",no);
		session.commit();
		FoodVO vo = session.selectOne("foodDetailData",no);
		session.close();
		return vo;
	}
	
	public static FoodVO foodCookieData(int no) {
		SqlSession session=ssf.openSession();
		FoodVO vo=session.selectOne("foodDetailData",no);
		session.close();
		return vo;
	}
	/**
	 * <select id="foodFindData" parameterType="hashmap" resultType="FoodVO">
		SELECT *
		FROM food2
		WHERE ${column} LIKE '%||#{fd}||%'
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	 */
	public static List<FoodVO> foodFindData(Map map){
		SqlSession session=ssf.openSession();
		List<FoodVO> list=session.selectList("foodFindData",map);
		session.close();
		return list;
	}
	/**
	 * <select id="foodFindTotalPage" parameterType="hashmap" resultType="int">
		SELECT CEIL(COUNT(*)/12.0)
		FROM food2
		WHERE ${column} LIKE '%||#{fd}||%'
	</select>
	 */
	public static int foodFindTotalPage(Map map) {
		SqlSession session=ssf.openSession();
		int total =session.selectOne("foodFindTotalPage",map);
		session.close();
		return total;
	}
}
