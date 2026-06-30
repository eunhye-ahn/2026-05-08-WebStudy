package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.ReplyVO;

public class ReplyDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	/**
	 * <select id="replyListData" resultType="ReplyVO" parameterType="hashmap">
		SELECT no,cno,rno,id,name,msg,TO_CHAR('yyyy-mm-dd hh24:mi:ss') as dbday
		FROM allReply
		WHERE cno=#{cno} AND rno=#{rno}
		ORDER BY no DESC
	</select>
	<insert id="replyInsert" parameterType="ReplyVO">
		INSERT INTO allReply VALUES(
			(SELECT NVL(MAX(no),1) FROM allReply),
			#{cno},#{rno},#{id},#{name},#{msg},SYSDATE
		)
	</insert>
	 */
	public static List<ReplyVO> replyListData(Map map){
		SqlSession session = ssf.openSession();
		List<ReplyVO> list = session.selectList("replyListData",map);
		session.close();
		return list;
	}
	public static void replyInsert(ReplyVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("replyInsert",vo);
		session.close();
	}
	/**
	 * <delete id="replyDelete" parameterType="int">
		DELETE FROM allReply
		WHERE no=#{no}
	</delete>
	 */
	public static void replyDelete(int no) {
		SqlSession session = ssf.openSession(true);
		session.delete("replyDelete",no);
		session.close();
	}
	/**
	 * <update id="replyUpdate" parameterType="ReplyVO">
		UPDATE allReply SET
		msg=#{msg}
		WHERE no=#{no}
	</update>
	 */
	public static void replyUpdate(ReplyVO vo) {
		SqlSession session = ssf.openSession(true);
		session.update("replyUpdate",vo);
		session.close();
	}
	
}
