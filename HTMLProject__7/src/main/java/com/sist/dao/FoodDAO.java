package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.*;

import java.sql.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public FoodDAO(){
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

	public List<FoodVO> foodListData(int page){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "select no, name, poster "
					+ "from food "
					+ "order by no asc "
					+ "offset ? rows fetch next 12 rows only";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, page);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	public int foodTotalPage() {
		int total =0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/12.0) from food";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			disConnection();
		}
		return total;
	}
	/**
	 *  NO                                        NOT NULL NUMBER
	 NAME                                               VARCHAR2(100)
	 TYPE                                               VARCHAR2(100)
	 PHONE                                              VARCHAR2(30)
	 ADDRESS                                            VARCHAR2(260)
	 SCORE                                              NUMBER(2,1)
	 PARKING                                            VARCHAR2(200)
	 POSTER                                             VARCHAR2(260)
	 TIME                                               VARCHAR2(200)
	 CONTENT                                            CLOB
	 THEME                                              VARCHAR2(4000)
	 PRICE                                              VARCHAR2(100)
	 */
	public FoodVO foodDetailPage(int no) {
		FoodVO vo = new FoodVO();
		try {
			getConnection();
			String sql = "select name, type, phone, address, score, parking, poster, time, content, theme, price "
					+ "from food "
					+ "where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setName(rs.getString(1));
			vo.setType(rs.getString(2));
			vo.setPhone(rs.getString(3));
			vo.setAddress(rs.getString(4));
			vo.setScore(rs.getDouble(5));
			vo.setParking(rs.getString(6));
			vo.setPoster(rs.getString(7));
			vo.setTime(rs.getString(8));
			vo.setContent(rs.getString(9));
			vo.setTheme(rs.getString(10));
			vo.setPrice(rs.getString(11));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	public static void main(String[] args) {
		FoodDAO dao = new FoodDAO();
		FoodVO vo = dao.foodDetailPage(1);
		System.out.println(vo.getPoster());
	}
}
