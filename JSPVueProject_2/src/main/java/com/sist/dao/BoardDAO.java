package com.sist.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BoardVO;

public class BoardDAO {
	
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <select id="boadListData" parameterType="int" resultType="BoardVO">
			SELECT no,name,subject,TO_CHAR(regdate,'yyyy-mm-dd') as dbday, hit
			FROM mvcdataboard
			ORDER BY no DESC
			OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
		</select>
		<select id="boadTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/12.0)
			FROM mvcdataboard
		</select>
	 */
	public static List<BoardVO> boadListData(int start){
		SqlSession session = ssf.openSession();
		List<BoardVO> list = session.selectList("boadListData",start);
		session.close();
		return list;
	}
	public static int boadTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("boadTotalPage");
		session.close();
		return total;
	}
	/**
	 * <update id="hitIncrement" parameterType="int">
		UPDATE mvcdataboard SET
		hit=hit+1
		WHERE no=#{no}
	</update>
	 * <select id="boardDetailData" parameterType="int" resultType="BoardVO">
		SELECT no,name,subject,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,content,hit
		FROM mvcdataboard
		WHERE no=#{no}
	</select>
	 */
	public static BoardVO boardDetailData(int no){
		SqlSession session = ssf.openSession();
		session.update("hitIncrement",no);
		session.commit();
		BoardVO vo = session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	/**
	 * 	<insert id="boardInsert" parameterType="BoardVO">
			<selectKey keyProperty="no" resultType="int" order="BEFORE">
				SELECT NVL(MAX(no)+1,1) as no FROM mvcdataboard
			</selectKey>
			INSERT INTO mvcdataboard(no,name,content,pwd,regdate,hit) VALUES(
				#{no},
				#{name},
				#{content},
				#{pwd},
				SYSDATE,
				0
			)
		</insert>
		
	 */
	public static void boardInsert(BoardVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert",vo);
		session.close();
	}
	/**
	 * <delete id="boardDelete" parameterType="int">
		DELETE FROM mvcdataboard WHERE no=#{no}
	</delete>
	
	유효성체크
	 */
	public static boolean boardDelete(int no,String pwd) {
		SqlSession session = ssf.openSession(true);
		boolean bCheck=false;
		String db_pwd = session.selectOne("pwdCheck",no);
		if(pwd.equals(db_pwd)) {
			bCheck=true;
			session.delete("boardDelete");
		}
		session.close();
		return bCheck;
	}
	/**
	 * <update id="boardUpdate" parameterType="BoardVO">
		UPDATE mvcdataboard SET 
		name=#{name},
		subject=#{subject}
		content=#{content}
		WHERE no=#{no}
	</update>
	<select id="pwdCheck" parameterType="int" resultType="string">
		SELECT password
		FROM mvcdataboard
		WHERE no=#{no}
	</select>
	
	유효성체크
	 */
	public static boolean boardUpdate(BoardVO vo) {
		SqlSession session = ssf.openSession(true);
		boolean bCheck=false;
		String db_pwd = session.selectOne("pwdCheck",vo.getNo());
		if(vo.getPwd().equals(db_pwd)) {
			bCheck=true;
			session.update("boardUpdate",vo);
		}
		session.close();
		return bCheck;
	}
}
