package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.ReviewVO;

public class ReviewDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/**
	 * <insert id="reviewInsert" parameterType="ReviewVO">
		<selectKey keyProperty="no" resultType="int" order="BEFORE">
			SELECT NVL(MAX(no)+1,1) as no FROM board
		</selectKey>
		INSERT INTO review(no,fno,id,name,msg)
		VALUES(#{no},#{fno},#{id},#{name},#{msg})
	</insert>
	<select id="reviewListData" parameterType="int" resultType="ReviewVO">
		SELECT no,fno,id,name,msg,TO_CHAR(regdate,'yyyy-mm-dd') as dbday
		FROM review
		WEHRE fno=#{fno}
		ORDER BY no DESC
	</select>
	 */
	public static List<ReviewVO> reviewListData(int no) {
		SqlSession session = ssf.openSession();
		List<ReviewVO> list = session.selectList("reviewListData",no);
		session.close();
		return list;
	}
	
	public static void reviewInsert(ReviewVO vo) {
		SqlSession session = ssf.openSession();
		session.insert("reviewInsert",vo);
		session.commit();
		session.close();
	}
	
	/**
	 * <delete id="reviewDelete" parameterType="int">
		DELETE FROM review
		WHERE no=#{fno}
	</delete>
	 */
	public static void reviewDelete(int no) {
		SqlSession session = ssf.openSession();
		session.insert("reviewDelete",no);
		session.commit();
		session.close();
	}
	
	/**
	 * <update id="reviewUpdate" parameterType="ReviewVO">
		UPDATE review SET
		msg=#{msg}
		WHERE no=#{no}
	</update>
	 */
	public static void reviewUpdate(ReviewVO vo) {
		SqlSession session = ssf.openSession();
		session.insert("reviewUpdate",vo);
		session.commit();
		session.close();
	}
}
