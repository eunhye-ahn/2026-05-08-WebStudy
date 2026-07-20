package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.*;

public class GoodsDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/**
	 * <select id="goodsListData" resultType="GoodsVO" parameterType="int">
		SELECT no,goods_name,goods_poster,goods_price,goods_first_price,reply_count,jjim_count,like_count
		FROM goods_all
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	<select id="goodsTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/12.0)
		FROM goods_all 
	</select>
	<update id="goodsHitIncrement" parameterType="int">
		UPDATE goods_all SET
		hit=hit+1
		WHERE no=#{no}
	</update>
	<select id="goodsDetailData" resultType="GoodsVO" parameterType="int">
		SELECT *
		FROM goods_all
		WHERE no=#{no}
	</select>
	 */
	public static List<GoodsVO> goodsListData(int start){
		SqlSession session = ssf.openSession();
		List<GoodsVO> list =session.selectList("goodsListData",start);
		session.close();
		return list;
	}
	public static int goodsTotalPage(){
		SqlSession session = ssf.openSession();
		int total =session.selectOne("goodsTotalPage");
		session.close();
		return total;
	}
	public static GoodsVO goodsDetailData(int no){
		SqlSession session = ssf.openSession();
		session.update("goodsHitIncrement",no);
		session.commit();
		GoodsVO vo =session.selectOne("goodsDetailData",no);
		session.close();
		return vo;
	}
}
