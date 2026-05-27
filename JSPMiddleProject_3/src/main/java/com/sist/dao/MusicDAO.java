package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static MusicDAO dao;
	
	//싱글톤(메모리 하나만 생성) -메모리누수방지,커넥션남발 방지
	public static MusicDAO newInstance() {
		if(dao == null) {
			dao = new MusicDAO();
		}
		return dao;
	}
	
	//드라이버등록
	public MusicDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//해제
	public void disConnection() {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//기능
	//목록출력
	public List<MusicVO> musicListData(int page){
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql = "select no,title,singer,album,poster,state,idcrement "
					+ "from genie_music "
					+ "where cno=1 "
					+ "order by no asc "
					+ "offset ? rows fetch next 20 rows only";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page*20)-20);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				vo.setPoster(rs.getString(5));
				vo.setState(rs.getString(6));
				vo.setIdcrement(rs.getInt(7));
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(); //=> 테스트방법 : Junit(단위테스트)
		}finally {
			disConnection();
		}
		return list;
	}
	
	//목록출력
//	public List<MusicVO> musicListData(){
//		List<MusicVO> list = new ArrayList<MusicVO>();
//		try {
//			getConnection();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disConnection();
//		}
//		return list;
//	}
}
