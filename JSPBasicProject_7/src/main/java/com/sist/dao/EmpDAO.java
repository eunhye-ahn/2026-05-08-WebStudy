package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;


public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static EmpDAO dao;
	// => 미리 생성된 커넥션 객체 주소값 얻기
	//DBCP => 웹에서만 사용가능 => 관리를 톰캣이 하므로
	public void getConnection() {
		try {
			//탐색기 형식으로 저장 (JNDI :java naming directory interface) 가상 디렉토리 생성기
			//1.탐색기열기
			Context init = new InitialContext();
			//2.c드라이브열기
			Context c = (Context)init.lookup("java://comp/env");
			//3.해당파일얻어오기
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			//4.connection 객체저장
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// => Pool 안으로 반환 => 재사용가능하도록
	public void disConnection() {
		try {
			if(ps != null) ps.close();		//반환
			if(conn != null) conn.close(); //반환
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// => 싱글톤
	public static EmpDAO newInstance() {
		if(dao == null) {
			dao = new EmpDAO(); 
		}
		return dao;
	}
	//기능
	public List<EmpBean> empListData(){
		List<EmpBean> list = new ArrayList<EmpBean>();
		try {
			getConnection();
			String sql = "select empno, ename, job, TO_CHAR(hiredate, 'yyyy-MM-dd'), sal "
					+ "from emp "
					+ "order by empno asc";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpBean bean = new EmpBean();
				bean.setEmpno(rs.getInt(1));
				bean.setEname(rs.getString(2));
				bean.setJob(rs.getString(3));
				bean.setDbday(rs.getString(4));
				bean.setSal(rs.getInt(5));
				list.add(bean);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection(); //반환(사용 후) => 다른사람이 사용가능
		}
		return list;
	}
}
