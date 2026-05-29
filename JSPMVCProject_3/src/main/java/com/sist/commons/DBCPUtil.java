package com.sist.commons;

import javax.sql.*; //datasource => db의 모든 정보를 갖고있다

import com.sist.dao.FoodDAO;

import javax.naming.*; //context => 저장 메모리 관리
import java.sql.*;
import java.util.*;

public class DBCPUtil {
	private Connection conn;
	
	//미리연결되어있는 커넥션 주소 얻어오기
	public Connection getConnection() {
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
		return conn;
	}
	//해제(반납)
	public void disConnection(Connection conn, PreparedStatement ps) {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
