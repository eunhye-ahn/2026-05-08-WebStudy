package com.sist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.sist.commons.*;
import javax.sql.*; //datasource => db의 모든 정보를 갖고있다
import javax.naming.*; //context => 저장 메모리 관리

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private DBCPUtil db = new DBCPUtil();
	
	private static FoodDAO dao;
	
	//기능
	//목록출력
	public List<FoodVO> foodListData(int page){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			db.getConnection();
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
			db.disConnection(conn, ps);
		}
		return list;
	}
	public int foodTotalpage() {
		int total = 0;
		try {
			db.getConnection();
			String sql = "select ceil(count(*)/10.0) from food";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.disConnection(conn,ps);
		}
		return total;
	}
	
	//상세보기
}
