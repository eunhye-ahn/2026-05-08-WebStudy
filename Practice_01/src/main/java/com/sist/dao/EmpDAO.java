package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.EmpVO;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public EmpDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL, "hr","happy");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<EmpVO> empListData(int page){
		List<EmpVO> list = new ArrayList<EmpVO>();
		try {
			getConnection();
			String sql = "select empno, ename, job "
					+ "from emp2 "
					+ "offset ? rows fetch next 10 rows only";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page*10)-10);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setNo(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	public int empTotalPage() {
		int total =0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/10.0) "
					+ "from emp2";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	public static void main(String[] args) {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empListData(1);
		for(EmpVO vo : list) {
			System.out.println(vo.getEname());
		}
		System.out.println(dao.empTotalPage());
	}
}
