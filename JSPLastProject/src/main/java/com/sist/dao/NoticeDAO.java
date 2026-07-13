package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.NoticeVO;
import com.sist.vo.ReviewVO;

public class NoticeDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
/**
 * <insert id="noticeInsert" parameterType="NoticeVO">
		INSERT INTO notice(no,type,subject,content)
		VALUES(notice_no_seq.nextval,#{type},#{subject},#{content})
	</insert>
 */
	public static void noticeInsert(NoticeVO vo) {
		SqlSession session = ssf.openSession();
		session.insert("noticeInsert",vo);
		session.commit();
		session.close();
	}
	/**
	<select id="noticeListData" parameterType="int" resultType="NoticeVO">
		SELECT no,name,subject,type,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit
		FROM notice
		ORDER BY no DESC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	 */
	public static List<NoticeVO> noticeListData(int start) {
		SqlSession session = ssf.openSession();
		List<NoticeVO> list = session.selectList("noticeListData",start);
		session.close();
		return list;
	}
	/**
	<select id="noticeTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/10.0)
		FROM notice
	</select>
	 */
	public static int noticeTotalPage() {
		SqlSession session = ssf.openSession();
		int total = session.selectOne("noticeTotalPage");
		session.close();
		return total;
	}
}
