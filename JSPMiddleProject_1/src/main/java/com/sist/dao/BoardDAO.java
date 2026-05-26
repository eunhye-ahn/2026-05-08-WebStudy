package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.BoardVO;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static BoardDAO dao;
	
	public static BoardDAO newInstance() {
		if(dao == null) {
			dao = new BoardDAO(); 
		}
		return dao;
	}
	
	public void getConnection() {
		try {
			//탐색기 형식으로 저장 (JNDI :java naming directory interface) 가상 디렉토리 생성기
			//1.탐색기열기
			Context init = new InitialContext();
			//2.c드라이브열기
			Context c = (Context)init.lookup("java://comp/env");
			//3.해당파일얻어오기
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			//4.connection 객체저장
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//사용후반환
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {}
	}
	
	//기능
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			//1.미리 만들어진 커넥션 주소 얻어오기
			getConnection();
			String sql = "select no, subject, name, regdate, hit "
					+ "from jspboard "
					+ "order by no desc "
					+ "offset ? rows fetch next 10 rows only";
			//2.sql문장 전송
			ps=conn.prepareStatement(sql);
			//3.실행요청 => ?에 값채우기
			ps.setInt(1, (page*10)-10); //0번부터 시작
			//4.실행후 결과값얻기
			ResultSet rs = ps.executeQuery();
			//5.리스트에 값채우기
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	public int boardTotalPage() {
		int totalpage = 0;
		try {
			//1.미리 만들어진 커넥션 주소 얻어오기
			getConnection();
			String sql = "select ceil(count(*)/10.0) from jspboard";
			//2.sql문장 전송
			ps=conn.prepareStatement(sql);
			//4.실행후 결과값얻기
			ResultSet rs = ps.executeQuery();
			//5.리스트에 값채우기
			rs.next();
			totalpage = rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return totalpage;
	}
	
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql = "insert into jspboard(name, subject, content, pwd) "
					+ "values(?,?,?,?)";
			ps=conn.prepareStatement(sql);
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
	public BoardVO boardDetail(int no) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql = "update jspboard set "
					+ "hit=hit+1 "
					+ "where no= ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			sql = "select no,name,subject,content,hit,TO_CHAR(regdate, 'yyyy-mm-dd hh24:mi:ss') "
					+ "from jspboard "
					+ "where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setDbday(rs.getString(6));
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
}
