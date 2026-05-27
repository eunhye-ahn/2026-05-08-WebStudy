package com.sist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*; //datasource => db의 모든 정보를 갖고있다
import javax.naming.*; //context => 저장 메모리 관리
/**
 * 1.pool안에 여러개의 커넥션 저장 (pool => 저장메모리공간)
 * 		maxActive : 접근자의 최대 커넥션 개수
 * 		maxIdle : pool안에 존재하는 커넥션 개수
 * 
 * 2. 사용자 요청시에 => pool에서 커넥션을 가지고 온다
 * 3. connection => 오라클 접근
 * 4. preparestatement => sql문장 수행
 * 5. connection 객체를 pool안으로 반환(재사용)
 */

/**
 * [BE]jsp -> mv -> mvc -> springframework -> spring-boot
 * [BE]jdbc -> dbcp -> mybatis -> jpa
 * oracle -> mysql -> mariaDB(AWS에 설치 => docker)
 * [SE]git action -> docker -> docker-compose -> 쿠버네티스 -> jinkins
 * 													 |nginex / ngrook(도메인)
 * [FE]javascript(바닐라js) - jquery(3,4)
 * 							|ajax
 * 						   vuejs
 * 							|vuex, pinia
 * 						   reactjs
 * 							|tanstack-query
 * 						   nodejs/typescript
 * 							|redux
 * 
 * jdbc : 시간이 오래걸린다 /보안취약
 * dbcp 기반 -> mybatis(isbatis) / xml(jsp-mvc) / annotation(spring)
 * 
 * 	=> 단점 : 웹에서만 사용이 가능(톰캣이 관리하므로, 단 mybatis는 사용가능)
 */

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static FoodDAO dao;
	
	//싱글톤(메모리 하나만 생성) -메모리누수방지,커넥션남발 방지
	public static FoodDAO newInstance() {
		if(dao == null) {
			dao = new FoodDAO();
		}
		return dao;
	}
	
	//미리연결되어있는 커넥션 주소 얻어오기
	public void getConnection() {
		try {
			//저장된 메모리 가져오기
			Context init = new InitialContext();
			//jndi => directory => 탐색기 => 탐색기 열기
			Context c = (Context)init.lookup("java://comp/env");
			//c드라이버
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			//파일 찾기
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//해제(반납)
	public void disConnection() {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
/**
TIME             VARCHAR2(200)  	-> theme
CONTENT          CLOB           	-> price
THEME            VARCHAR2(4000) 	-> content
PRICE            VARCHAR2(100) 		->time
 */
	//기능
	//목록출력
	public List<FoodVO> foodListData(int page){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "select no,poster,name,address "
					+ "from food "
					+ "order by no asc "
					+ "offset ? rows fetch next 12 rows only";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, page);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setAddress(rs.getString(4));
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	public int foodTotalpage() {
		int total = 0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/10.0) from food";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	
	//상세보기
}
