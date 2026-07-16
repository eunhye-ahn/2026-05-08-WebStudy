package com.sist.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;

public class RecommandDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/**
	 * <select id="foodGetType" resultType="string" parameterType="string">
		select type from food2
		where no=(
			select fno
			FROM (
			    select fno, count(*) as review_cnt
			    from review
			    where id=#{id}
			    group by fno
			    order by review_cnt desc
			)
			where rownum=1
		);
	</select>
	<select id="memberGetAddress" resultType="string" parameterType="string">
		SELECT addr1 FROM member
		WHERE id=#{id}
	</select>
	<select id="foodRecommandData" resultType="FoodVO" parameterType="hashmap">
		select * 
		from(select * from food2 
			where type like '%'||#{type}||'%' and address like '%'||${address}||'%' 
			order by score desc)
		where rownum&lt;=5;
	</select>
	 */
	public static String foodGetType(String id) {
		SqlSession session = ssf.openSession();
		String type = session.selectOne("foodGetType",id);
		session.close();
		return type;
	}
	public static String memberGetAddress(String id) {
		SqlSession session = ssf.openSession();
		String addr1 = session.selectOne("memberGetAddress",id);
		session.close();
		return addr1;
	}
	public static List<FoodVO> foodRecommandData(String id){
		SqlSession session = ssf.openSession();
		String type = session.selectOne("foodGetType",id);
		String address = session.selectOne("memberGetAddress",id);
		String[] addrs=address.split(" ");
		Map map = new HashMap();
		map.put("address", addrs[1]);
		map.put("type", type);
		List<FoodVO> list = session.selectList("foodRecommandData",map);
		session.close();
		return list;
	}
}
