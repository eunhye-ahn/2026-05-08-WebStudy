package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.*;
import com.sist.vo.*;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/**
	 * <select id="memberIdCount" resultType="int" parameterType="string">
			SELECT COUNT(*)
			FROM member
			WHERE id = #{id}
		</select>
		<select id="memberGetPassword" parameterType="string" resultType="MemberVO">
			SELECT id,name,pwd
			FROM member
			WHERE id=#{id}
		</select>
	 */
	public static MemberVO memberLogin(String id, String pwd) {
		MemberVO vo = new MemberVO();
		SqlSession session = ssf.openSession();
		int count = session.selectOne("memberIdCount",id);
		if(count == 0) {
			vo.setMsg("NOID");
		}
		else {
			MemberVO dbVO = session.selectOne("memberGetPassword",id);
			if(pwd.equals(dbVO.getPwd())) {
				vo.setId(dbVO.getId());
				vo.setName(dbVO.getName());
				vo.setMsg("OK");
			}
			else {
				vo.setMsg("NOPWD");
			}
		}
		session.close();
		return vo;
	}
}
