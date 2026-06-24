package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;

public class GoodsDAO {
	public static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <select id="goodsListData" resultMap="goodsMap" parameterType="int">
			SELECT no,goods_name,goods_poster,goods_price
			FROM goods_all
			ORDER BY no ASC
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
		<select id="goodsTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/12.0)
			FROM goods_all
		</select>
	 */
	public List<GoodsVO> goodsListData(int start){
		SqlSession session = ssf.openSession();
		List<GoodsVO> list = session.selectList("goodsListData",start);
		session.close();
		return list;
	}
	public int goodsTotalPage(){
		SqlSession session = ssf.openSession();
		int total = session.selectOne("goodsTotalPage");
		session.close();
		return total;
	}
}
