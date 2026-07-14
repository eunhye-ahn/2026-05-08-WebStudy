package com.sist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.MemberVO;

public class AdminDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/**
	 * <select id="memberListData" parameterType="int" resultType="MemberVO">
		SELECT id,name,addr1,sex,phone,grade
		FROM member2
		WHERE admin!='y'
		ORDER BY regdate DESC
		OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY
	</select>
	<select id="memberTotalpage" resultType="int">
		SELECT CEIL(COUNT(*)/10.0)
		FROM member2
	</select>
	 */
	public static List<MemberVO> memberListData(int start){
		SqlSession session = ssf.openSession();
		List<MemberVO> list = session.selectList("memberListt",start);
		session.close();
		return list;
	}
	public static int memberTotalpage(){
		SqlSession session = ssf.openSession();
		int total = session.selectOne("memberTotalpage");
		session.close();
		return total;
	}
	/**
	 * <update id="membergradeChange" parameterType="MemberVO">
		UPDATE member2 SET
			grade=#{grade}
		WHERE id=#{id}
	</update>
	
	insert update delete => return형 int (0:오류/1이상:정상)
	 */
	public static int membergradeChange(MemberVO vo){
		SqlSession session = ssf.openSession();
		int count = session.update("membergradeChange",vo);
		session.commit();
		session.close();
		return count;
	}
}
