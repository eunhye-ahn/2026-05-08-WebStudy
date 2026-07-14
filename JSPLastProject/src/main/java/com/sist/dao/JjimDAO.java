package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.JjimVO;
import com.sist.vo.TourVO;

public class JjimDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
/**
 * <select id="jjimCheck" parameterType="JjimVO" resultType="int">
		SELECT COUNT(*)
		FROM jjim
		WHERE fno=#{fno} AND id=#{id}
	</select>
 */
	public static int jjimCheck(JjimVO vo) {
		SqlSession session = ssf.openSession();
		int count = session.selectOne("jjimCheck",vo);
		session.close();
		return count;
	}
	/**
	 * <insert id="jjimOn" parameterType="JjimVO">
		INSERT INTO jjim(no,fno,id)
		VALUES(jjim_jno_seq,#{fno},#{id})
	</insert>
	 */
	public static void jjimOn(JjimVO vo) {
		SqlSession session = ssf.openSession();
		session.insert("jjimOn",vo);
		session.commit();
		session.close();
	}
	/**
	 * <select id="jjimListData" parameterType="string" resultType="jjimMap">
		SELECT fno,jno,name,poster,TO_CHAR(regdate,'yyyy-mm-dd') as dbday
		FROM jjim JOIN food2
		ON jjim.fno=food.no AND id=#{id}
		ORDER BY no DESC
	</select>
	 */
	public static List<JjimVO> jjimListData(String id) {
		SqlSession session = ssf.openSession();
		List<JjimVO> list = session.selectList("jjimListData",id);
		session.close();
		return list;
	}
	/**
	 * <delete id="jjimOff" parameterType="JjimVO">
		DELETE FROM jjim
		WHERE fno=#{fno} AND id=#{id}
	</delete>
	 */
	public static void jjimOff(int jno) {
		SqlSession session = ssf.openSession();
		session.delete("jjimOff",jno);
		session.commit();
		session.close();
	}
}
