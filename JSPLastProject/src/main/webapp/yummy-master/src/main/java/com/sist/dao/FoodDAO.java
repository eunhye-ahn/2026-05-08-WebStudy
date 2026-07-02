package com.sist.dao;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import com.sist.vo.*;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//파싱 후에는 ConnectionPool => Connection저장 : getConnection/disConnection
	
/**
 * fdArr / start
 * <select id="foodFindData" parameterType="hashmap" resultType="FoodVO">
		SELECT no,name,poster,address,type
		FROM food
		WHERE 
		<trim prefix="(" suffix=")" prefixOverrides="OR|AND">
			<foreach collection="fdArr" item="fd">
				<trim prefix="OR">
					<choose>
						<when test="fd=='A'.toString()">
							type LIKE '%한식%'
						</when>
						<when test="fd=='B'.toString()">
							type LIKE '%양식%'
						</when>
						<when test="fd=='C'.toString()">
							type LIKE '%일식%'
						</when>
						<when test="fd=='D'.toString()">
							type LIKE '%중식%'
						</when>
						<when test="fd=='E'.toString()">
							type LIKE '%분식%'
						</when>
					</choose>
				</trim>
			</foreach>
		</trim>
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY 
	</select>
	//fdarr
	<select id="foodFindCount" resultType="int" parameterType="hashmap">
		SELECT COUNT(*) FROM food
		WHERE 
		<trim prefix="(" suffix=")" prefixOverrides="OR|AND">
			<foreach collection="fdArr" item="fd">
				<trim prefix="OR">
					<choose>
						<when test="fd=='A'.toString()">
							type LIKE '%한식%'
						</when>
						<when test="fd=='B'.toString()">
							type LIKE '%양식%'
						</when>
						<when test="fd=='C'.toString()">
							type LIKE '%일식%'
						</when>
						<when test="fd=='D'.toString()">
							type LIKE '%중식%'
						</when>
						<when test="fd=='E'.toString()">
							type LIKE '%분식%'
						</when>
					</choose>
				</trim>
			</foreach>
		</trim>
	</select>
	
	AJAX로 보내든 JSP를 보내든 DAO/VO는 변경없다
	Model => list : 일반JSP / [] : AJAX/Vue/React
 */
	public static List<FoodVO> foodFindData(Map map){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData",map);
		session.close();
		return list;
	}
	public static int foodFindCount(Map map){
		SqlSession session = ssf.openSession();
		int count = session.selectOne("foodFindCount",map);
		session.close();
		return count;
	}
}
