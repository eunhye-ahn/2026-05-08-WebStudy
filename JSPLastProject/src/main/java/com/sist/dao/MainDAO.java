package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;
import com.sist.vo.TourVO;

public class MainDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
/**
 * <select id="mainFoodTop7" resultType="FoodVO">
		SELECT no,poster,name,type,phone,address,score,theme,time,reserve,parking,price,content,images,jjimcount,hit
		FROM (SELECT no,poster,name,type,phone,address,score,theme,time,reserve,parking,price,content,images,jjimcount,hit
			FROM food2
			ORDER BY hit DESC
			)
		WHERE rownum&lt;=4
 * @return
 */
	public static List<FoodVO> mainFoodTop7(){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("mainFoodTop7");
		session.close();
		return list;
	}
	/**
	 * <select id="mainPusanTop4" resultType="TourVO">
		SELECT contentid,firstimage,addr1,likecount,replycount,rownum,title
		FROM (SELECT contentid,firstimage,addr1,likecount,replycount,rownum,title
			FROM tour
			WHERE areacode=6 AND contenttypeid=12
			)
		WHERE rownum&lt;=4
	</select>
	<select id="mainJejuTop4" resultType="TourVO">
		SELECT contentid,firstimage,addr1,rownum,title
		FROM (SELECT contentid,firstimage,addr1,rownum,title
			FROM tour
			WHERE areacode=39 AND contenttypeid=12
			)
		WHERE rownum&lt;=4
	</select>
	 * @return
	 */
	public static List<TourVO> mainPusanTop4(){
		SqlSession session = ssf.openSession();
		List<TourVO> list = session.selectList("mainPusanTop4");
		session.close();
		return list;
	}
	public static List<TourVO> mainJejuTop4(){
		SqlSession session = ssf.openSession();
		List<TourVO> list = session.selectList("mainJejuTop4");
		session.close();
		return list;
	}
	
}
