package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.vo.FoodVO;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public FoodDAO() {
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
	
	public List<FoodVO> foodListData(int page, String type){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "select no, name, poster "
					+ "from food "
					+ "where type like '%'||?||'%' "
					+ "offset ? rows fetch next 12 rows only";
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ps.setInt(2, (page*12)-12); //offset은 시작번호 => 0번부터
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
	public int foodTotalPage(String type) {
		int total=0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/12.0) "
					+ "from food "
					+ "where type like '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
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
	
	//검색
	public List<FoodVO> foodFindData(String column, String fd){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "select no, name, poster "
					+ "from food "
					+ "where "+column+" like '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fd);
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
	
	public static void main(String[] args) {
		FoodDAO dao = new FoodDAO();
		List<FoodVO> list = dao.foodListData(1, "한식");
		for(FoodVO vo : list) {
			System.out.println(vo.getPoster());
		}
		int total = dao.foodTotalPage("양식");
		System.out.println(total);
		List<FoodVO> find = dao.foodFindData("type", "양식");
		for(FoodVO vo : find) {
			System.out.println(vo.getPoster());
		}
	}
	

}
