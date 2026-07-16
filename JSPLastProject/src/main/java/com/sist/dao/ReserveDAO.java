package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;
import com.sist.vo.ReserveVO;

public class ReserveDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/**
	 * <select id="reserveFoodListData" resultType="FoodVO" parameterType="hashmap">
		SELECT no,name,poster,type
		FROM food2
		WHERE type LIKE '%'||#{type}||'%'
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 20 ROWS ONLY
	</select>
	<select id="reserveFoodTotalpage" resultType="int" parameterType="string">
		SELECT CEIL(COUNT(*)/20.0)
		FROM food2
		WHERE type LIKE '%'||#{type}||'%'
	</select>
	 */
	public static List<FoodVO> reserveFoodListData(Map map){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("reserveFoodListData",map);
		session.close();
		return list;
	}
	public static int reserveFoodTotalpage(String type){
		SqlSession session = ssf.openSession();
		int total = session.selectOne("reserveFoodTotalpage",type);
		session.close();
		return total;
	}
	/**
	 * <insert id="reserveInsert" parameterType="ReserveVO">
		INSERT INTO reserve(rno,fno,id,name,rdate,rtime,inwon)
		VALUES(res_rno_seq.nextval,#{fno},#{id},#{name},#{rdate},#{rtime},#{inwon})
	</insert>
	 */
	public static void reserveInsert(ReserveVO vo){
		SqlSession session = ssf.openSession();
		session.insert("reserveInsert",vo);
		session.commit();
		session.close();
	}
	/**
	 * <select id="reserveListData" resultMap="reserveMap" parameterType="string">
		SELECT rno,fno,r.name,rdate,rtime,inwon,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,
		f.name,poster,phone
		FROM reserve r JOIN food f
		ON r.fno=f.no AND id=#{id}
		ORDER BY rno DESC
	</select>
	 */
	public static List<ReserveVO> reserveListData(String id) {
		SqlSession session = ssf.openSession();
		List<ReserveVO> list = session.selectList("reserveListData",id);
		session.close();
		return list;
	}
	/**
	 * <select id="reserveAdiminListData" resultMap="reserveMap" parameterType="string">
		SELECT rno,fno,r.name,rdate,rtime,inwon,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,
		f.name,poster,phone,ok
		FROM reserve r JOIN food2 f
		ON r.fno=f.no
		ORDER BY rno DESC
	</select>
	 */
	public static List<ReserveVO> reserveAdiminListData() {
		SqlSession session = ssf.openSession();
		List<ReserveVO> list = session.selectList("reserveAdiminListData");
		session.close();
		return list;
	}
	/**
	 * <update id="reserveAdminOk" parameterType="int">
		UPDATE reserve SET
		ok='Y'
		WHERE rno=#{rno}
	</update>
	 */
	public static void reserveAdminOk(int rno){
		SqlSession session = ssf.openSession();
		session.update("reserveAdminOk",rno);
		session.commit();
		session.close();
	}
	/**
	 * <delete id="reserveCancel" parameterType="int">
			DELEETE FROM reserve
			WHERE rno=#{rno}
		</delete>
	 */
	public static void reserveCancel(int rno){
		SqlSession session = ssf.openSession();
		session.delete("reserveCancel",rno);
		session.commit();
		session.close();
	}
	/**
	 * <select id="reserveInfo" parameterType="int" resultMap="infoMap">
			SELECT rno,no,rdate,rtime,inwon,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,
			f.name as fname,poster,address,parking,type,score,time,phone
			FROM reserve r JOIN food2 f
			ON r.fno=f.no AND rno=#{rno} 
		</select>
	 */
	public static ReserveVO reserveInfo(int rno) {
		SqlSession session = ssf.openSession();
		ReserveVO vo = session.selectOne("reserveInfo",rno);
		session.close();
		return vo;
	}
}
