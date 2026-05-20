package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL= "jdbc:oracle:thin:@localhost:1521:XE";
	public EmpDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	public void disConnection() {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql = "select empno, ename, job, TO_CHAR(hiredate,'yyyy-MM-dd'), "
					+ "sal, dname, loc "
					+ "from emp join dept "
					+ "on emp.deptno = dept.deptno "
					+ "order by sal desc";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setSal(rs.getInt(5));
				vo.getDvo().setDname(rs.getString(6));
				vo.getDvo().setLoc(rs.getString(7));
				list.add(vo);
			}
		}catch(Exception e) {}
		finally {
			disConnection();
		}
		return list;
	}
	
	public static void main(String[] args) {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empListData();
		for(EmpVO vo : list) {
			System.out.println(vo.getEname());
		}
	}
}
