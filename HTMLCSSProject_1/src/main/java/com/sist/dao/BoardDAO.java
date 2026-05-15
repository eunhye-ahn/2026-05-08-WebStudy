package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.sist.vo.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public BoardDAO(){
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
	
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql = "select no, subject, name, TO_CHAR(regdate,'yyyy-MM-dd'),hit "
					+ "from jspBoard "
					+ "order by no desc "
					+ "offset ? rows fetch next 10 rows only";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page*10)-10);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	public int boardTotalPage() {
		int total = 0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/10.0) "
					+ "from jspBoard";
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
	
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql = "insert into jspBoard(name, subject, content, pwd) "
					+ "values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	public BoardVO boardDetailData(int no) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql = "update jspBoard set "
					+ "hit=hit+1 "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			//게시글읽기
			sql = "select no, name, subject, content, hit,"
					+ "TO_CHAR(regdate, 'yyyy-MM-dd hh24:mi:ss') "
					+ "from jspBoard "
					+ "where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setDbday(rs.getString(6));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	public boolean boardDeleteData(String pwd, int no) {
		boolean bcheck = false;
		try {
			getConnection();
			String sql = "select pwd "
					+ "from jspBoard "
					+ "where no = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) {
				bcheck=true;
				sql = "delete from jspBoard "
						+ "where no = ?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
			else {
				bcheck=false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return bcheck;
	}
	
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.boardListData(1);
		for(BoardVO vo : list) {
			System.out.println(vo.getName());
		}
		System.out.println(dao.boardTotalPage());
		
		System.out.println(dao.boardDetailData(1));
	}
}
