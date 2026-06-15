package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;
public class ReplyDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/**
	 * <select id="replyListData" parameterType="int" resultType="ReplyVO">
			SELECT no,fno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday
			FROM reply
			WHERE fno = #{fno}
			ORDER BY no DESC
		</select>
		<insert id="replyInsert" parameterType="ReplyVO">
			<!-- 
			시퀀스 
			order => 먼저실행
			-->
			<selectKey keyProperty="no" resultType="int" order="BEFORE">
				SELECT NVL(MAX(no)+1,1) as no FROM reply
			</selectKey>
				INSERT INTO reply 
				VALUES(#{no),#{fno},#{id},#{name},#{msg},SYSDATE)
		</insert>
	 */
	public static List<ReplyVO> replyListData(int fno){
		SqlSession session = ssf.openSession();
		List<ReplyVO> list = session.selectList("replyListData",fno);
		session.close();
		return list;
	}
	
	public static void replyInsert(ReplyVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("replyInsert",vo);
		session.close();
	}
}
