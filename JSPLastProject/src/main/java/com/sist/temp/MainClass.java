package com.sist.temp;
import java.sql.*;


public class MainClass {
	public static void main(String[] args) {
		System.out.println("main 실행");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url,"hr","hr");
			conn.setAutoCommit(false);
			String sql="UPDATE goods_all SET "
					+ "stock=? "
					+ "WHERE no=?";
			for(int i=1;i<=6184;i++) {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, (int)(Math.random()*11)+10);
				ps.setInt(2, i);
				ps.executeUpdate();
				ps.close();
				System.out.println(i+"ROW injection");
			}
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			}catch(Exception ex) {
				
			}
		}
	}
	
}
