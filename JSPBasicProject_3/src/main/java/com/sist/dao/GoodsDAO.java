package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static GoodsDAO dao = null;
	public GoodsDAO(){
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
	
	//싱글턴 생성
	public static GoodsDAO newInstance() {
		if(dao == null)
			dao = new GoodsDAO();
		return dao;
	}
	
	public List<GoodsVO> goodsListData(int page) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();

		try {
			getConnection();
			String sql = "select no,goods_name,goods_poster,goods_price "
					+ "from goods_all "
					+ "order by no asc "
					+ "offset ? rows fetch next 12 rows only";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page*12)-12);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo = new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
				vo.setGoods_price(rs.getString(4));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	public int goodsTotalData() {
		int total = 0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/12.0) from goods_all";
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
	
	public GoodsVO goodsDetailData(int no) {
		GoodsVO vo = new GoodsVO();
		try {
			getConnection();
			String sql = "update goods_all set hit=hit+1 where no="+no;
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql= "select no, goods_name, goods_poster, goods_price, goods_sub, goods_discount, goods_delivery "
					+ "from goods_all "
					+ "where no="+no;
			ps= conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setGoods_name(rs.getString(2));
			vo.setGoods_poster(rs.getString(3));
			vo.setGoods_price(rs.getString(4));
			vo.setGoods_sub(rs.getString(5));
			vo.setGoods_discount(rs.getInt(6));
			vo.setGoods_delivery(rs.getString(7));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	public static void main(String[] args) {
	    GoodsDAO dao = new GoodsDAO(); 
	    GoodsVO vo = dao.goodsDetailData(1);
	    System.out.println(vo.getGoods_name());
	    	
	}
}
