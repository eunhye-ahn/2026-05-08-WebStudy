package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodLikeVO;

public class FoodLikeDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}

	/**
	 * <insert id="foodLikeInsert" parameterType="FoodLikeVO">
		INSERT INTO food_like(id,fno,member_id)
		VALUES(id=#{id}, fno=#{fno}, member_id=#{member_id})
	</insert>
	 */
	public static void foodLikeInsert(FoodLikeVO vo) {
		SqlSession session = ssf.openSession();
		session.insert("foodLikeInsert",vo);
		session.commit();
		session.close();
	}
	public static void foodLikeDelete(FoodLikeVO vo) {
		SqlSession session = ssf.openSession();
		session.delete("foodLikeDelete",vo);
		session.commit();
		session.close();
	}
	/**
	 * 
		<select id="foodLikeChecked" parameterType="FoodLikeVO">
			SELECT COUNT(*)
			FROM food_like
			WHERE member_id=#{member_id} AND fno=#{fno}
		</select>
	 */
	public static boolean foodLikeChecked(FoodLikeVO vo) {
		SqlSession session = ssf.openSession();
		int count = session.selectOne("foodLikeChecked",vo);
		boolean likeCheck = false;
		if(count != 0) {
			likeCheck = true;
		}
		session.close();
		return likeCheck;
	}
	

}
