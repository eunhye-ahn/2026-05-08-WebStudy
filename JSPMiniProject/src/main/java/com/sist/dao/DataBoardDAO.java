package com.sist.dao;

import java.util.*;
/**
 * 오라클
 * SQL
 * -DQL
 * 	SELECT 
 * -DML
 * 	INSERT UPDATE DELETE
 * -DDL
 * 	CREATE DROP ALTER TRUNCATE RENAME
 * -DCL
 * 	GRANT REVOKE
 * -TCL
 * 	COMMIT ROLLBACK SAVEPOINT
 * 
 * 1. SELECT : 데이터 검색
 * select 
 * from table|view|select~
 * [
 * 	where 조건
 * 	group by
 * 	having
 * 	order by
 * ]
 * 
 * =>가장 많이 사용 기술
 * -조인
 * 	inner join
 * 		select a.col,b.col
 * 		from a,b
 * 		where a.col=b.col
 * 
 * 		select a.col,b.col
 * 		from a join b
 * 		on a.col=b.col
 * 
 * 	outer join
 * 		select a.col,b.col
 * 		from a,b
 * 		where a.col=b.col(+)
 * 
 * 		select a.col,b.col
 * 		from a join b
 * 		on a.col=b.col(+)
 * 
 * 	left join
 * 		select a.col,b.col
 * 		from a,b
 * 		where a.col=b.col(-)
 * 
 * 		select a.col,b.col
 * 		from a join b
 * 		on a.col=b.col(-)
 * 			
 	
 * -서브쿼리
 * 	인라인뷰
 * 	=>select
 * 		from(select ~) => table 대체
 * 	=>스칼라 서브쿼리
 * 		select (select~),column => column 대체
 * 		from table_name
 * ---------------------DBA
 * =>복잡한 쿼리 : view/function
 * =>공통으로 사용되는 부분 : 댓글,좋아요,찜하기
 * 		=>procedure
 * 	=> 다른테이블 연결 : 트리거
 * --------------------------------------
 * 	insert 
 * 		insert into table_name VALUES(...)
 * 					---------- 컬럼 전체의 값 주입
 * 					---------- default가 적용안됨
 * 		insert into table_name (컬럼,컬럼,...) values(값,값,...)
 * 		varchar2 / clob => '값'
 * 		날짜 => sysdate
 * 		예약일 => 'yy/mm/dd' => date / varchar2
 * 	update
 * 		update table_name set
 * 		컬럼=값, 컬럼=값...
 * 		where 조건
 * 	delete	
 * 		delete from table_name
 * 		where 조건
 * ----------------------------------------- 
 * 
 * DDL
 * 
 */

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.sist.commons.*;
import com.sist.vo.DataBoardVO;

public class DataBoardDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	/*
	 * <select id="boardListData" resultType="DataBoardVO" parameterType="int">
			SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd') as dbday, hit
			FROM mvcdataboard
			ORDER BY no DESC
			OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY
		</select>
		<select id="boardROWCOUNT" resultType="int">
			SELECT COUNT(*) FROM mvcdataboard
		</select>
	 */
	public static List<DataBoardVO> boardListData(int start){
		SqlSession session = ssf.openSession();
		List<DataBoardVO> list = session.selectList("boardListData",start);
		session.close();
		return list;
	}
	public static int boardROWCOUNT(){
		SqlSession session = ssf.openSession();
		int total = session.selectOne("boardROWCOUNT");
		session.close();
		return total;
	}
	/**
	 * <insert id="boardInsert" parameterType="DataBoardVO">
			<selectKey keyProperty="no" resultType="int" order="BEFORE">
			<!-- 시퀀스 -->
				SELECT NVL(MAX(no)+1,1) as no FROM mvcdataboard
			</selectKey>
			INSERT INTO mvcdataboard(no,name,subject,content,pwd,filename,filesize) 
			VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize})
		</insert>
	 */
	public static void boardInsert(DataBoardVO vo) {
		SqlSession session = ssf.openSession(true);
		session.insert("boardInsert",vo);
		session.close();
	}
	/*
	 * <update id="boardHitIncrement" parameterType="int">
		 	UPDATE mvcdataboard SET
		 	hit=hit+1
		 	WHERE no=#{no}
		 </update>
		 <select id="boardDetailData" parameterType="int" resultType="DataBoardVO">
		 	SELECT no,name,subject,content,TO_CHAR(regdate,'yyyy-mm-dd hh24:mi:ss') as dbday,filename,filesize
		 	FROM mvcdataboard
		 	WHERE no=#{no}
		 </select>
	 */
	public static DataBoardVO boardDetailData(int no) {
		SqlSession session = ssf.openSession();
		session.update("boardHitIncrement",no);
		session.commit();
		DataBoardVO vo = session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	/**
	 * <select id="boardGetPassword" parameterType="int" resultType="string">
		 	SELECT pwd FROM mvcdataboard
		 	WHERE no=#{no}
		 </select>
		 <update id="boardUpdate" parameterType="DataBoardVO">
		 	UPDATE mvcdataboard SET
		 	name=#{name},subject=#{subject},content=#{content},filename=#{filename},filesize=#{filesize}
		 	WHERE no=#{no}
		 </update>
	 */
	
	//update 전 이전 데이터 가져오기
	public static DataBoardVO boardUpdateData(int no) {
		SqlSession session = ssf.openSession();
		DataBoardVO vo = session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	public static boolean boardUpdate(DataBoardVO vo) {
		SqlSession session = ssf.openSession(true);
		boolean bCheck = false;
		String db_pwd = session.selectOne("boardGetPassword",vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			session.update("boardUpdate",vo);
		}
		
		return bCheck;
	}
}
