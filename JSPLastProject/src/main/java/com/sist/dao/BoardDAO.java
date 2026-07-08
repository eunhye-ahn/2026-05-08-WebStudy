package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.BoardVO;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/**
	 * <select id="boardListData" parameterType="int" resultType="BoardVO">
		SELECT *
		FROM board
		ORDER BY no DESC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	<select id="boardTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/12.0) FROM board
	</select>
	 */
	public static List<BoardVO> boardListData(int start){
		SqlSession session = ssf.openSession();
		List<BoardVO> list = session.selectList("boardListData",start);
		session.close();
		return list;
	}
	public static int boardTotalPage(){
		SqlSession session = ssf.openSession();
		int total = session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	/**
	 * <insert id="boardInsert" parameterType="BoardVO">
		<selectKey keyProperty="no" resultType="int" order="BEFORE">
			SELECT NVL(MAX(no)+1,1) as no FROM board
		</selectKey>
		INSERT INTO board VALUES(
			#{no},
			#{name},
			#{subject},
			#{content},
			#{pwd},
			SYSDATE,
			0
		)
	</insert>
	 */
	public static void boardInsert(BoardVO vo){
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert",vo);
		session.close();
	}
	/**
	 * <select id="boardDetailData" parameterType="int" resultType="BoardVO">
		SELECT no,name,subject,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday, content,password
		FROM board
		WHERE no=#{no}
	</select>
	 */
	/**
	 * <update id="boardHitIncrement" parameterType="int">
		UPDATE board SET
		hit=hit+1
		WHERE no=#{no}
	</update>
	 */
	public static BoardVO boardDetailData(int no){
		SqlSession session = ssf.openSession();
		session.update("boardHitIncrement",no);
		session.commit();
		BoardVO vo =session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	/**
	 * <select id="boardPwdCheck" parameterType="int" resultType="string">
		SELECT pwd
		FROM board
		WHERE no=#{no}
	</select>
	<delete id="boardDelete" parameterType="int">
		DELETE FROM board WHERE no=#{no}
	</delete>
	 */
	public static boolean boardDeleteData(int no,String pwd) {
		SqlSession session = ssf.openSession(true);
		String db_pwd = session.selectOne("boardPwdCheck",no);
		boolean bCheck = false;
		if(db_pwd.trim().equals(pwd.trim())) {
			bCheck=true;
			session.delete("boardDelete",no);
		}
		session.close();
		return bCheck;
	}
	/**
	 * <update id="boardUpdate" parameterType="BoardVO">
		UPDATE board SET(
			name = #{name},
			subject = #{subject},
			content = #{content},
			pwd = #{pwd}
		)
		WHERE no=#{no}
	</update>
	 */
	public static boolean boardUpdate(BoardVO vo) {
		SqlSession session = ssf.openSession(true);
		String db_pwd = session.selectOne("boardPwdCheck",vo.getNo());
		boolean bCheck = false;
		if(db_pwd.trim().equals(vo.getPwd().trim())) {
			bCheck=true;
			session.update("boardUpdate",vo);
		}
		session.close();
		return bCheck;
	}
}
