package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;


public class FoodDAO {
	//연결객체
	private Connection conn;
	//송수신객체
	private PreparedStatement ps;
	//url주소
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; 
	
	//드라이버등록
	public FoodDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//오라클연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//오라클 닫기
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//목록출력
	public List<FoodVO> foodListData(int page){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "select no,name,poster,type,address "
					+ "from food "
					+ "order by no asc "
					+ "offset ? rows fetch next 20 rows only";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page*20)-20);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setType(rs.getString(4));
				vo.setAddress(rs.getString(5));
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
	
	//총페이지
	public int foodTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/20.0) from food";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception e){
			
		}finally {
			disConnection();
		}
		return total;
	}
}
