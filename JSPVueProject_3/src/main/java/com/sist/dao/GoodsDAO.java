package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.GoodsVO;

public class GoodsDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	
	/**
	 * <select id="goodsListData" parameterType="int" resultType="GoodsVO">
		SELECT no, goods_name, goods_price
		FROM goods_all
		ORDER BY 1
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	<select id="goodsTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/12.0)
		FROM goods_all
	</select>
	 */
	public static List<GoodsVO> goodsListData(int start){
		SqlSession session = ssf.openSession();
		List<GoodsVO> list = session.selectList("goodsListData",start);
		session.close();
		return list;
	}
	public static int goodsTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("goodsTotalPage");
		session.close();
		return total;
	}
	/**
	 * <update id="hitIncrement" parameterType="int">
		UPDATE goods_all SET 
		hit=hit+1
		WHERE no=#{no}
	</update>
	<select id="goodsDetailData" resultType="goodsVO" parameterType="int">
		SELECT no,goods_name,goods_price,goods_poster
		FROM goods_all
		WHERE no=#{no}
	</select>
	 */
	public static GoodsVO goodsDetailData(int no) {
		SqlSession session = ssf.openSession();
		session.update("hitIncrement",no);
		session.commit();
		GoodsVO vo = session.selectOne("goodsDetailData",no);
		session.close();
		return vo;
	}
}
