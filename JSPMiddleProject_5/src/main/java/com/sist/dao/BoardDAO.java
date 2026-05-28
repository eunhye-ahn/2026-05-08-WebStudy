package com.sist.dao;

import java.util.*;
import java.security.DrbgParameters.Reseed;
import java.sql.*;		
import javax.sql.*;		//datasource => 데이터베이스 연결에 필요한 정보
import javax.naming.*;	//context => pool안에 저장됨 커넥션주소 얻기

//pool => 메모리 공간 (connection을 생성 => 저장공간)
//pool 안에 있는 커넥션 주소를 대여 => 사용 후 pool안으로 반환 (재사용 가능: 커넥션 수 한정)
// 커넥션 관리 용이, 미리 연결 (속도 빠름) => dbcp를 라이브러리화 : mybatis/jpa => connection pool 기반
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static BoardDAO dao; //싱글톤 => 메모리 누수현상 방지 => new를 쓰면 어딨는지 몰라서 삭제할 수 없음(일정시간 대기해야 자동삭제됨 => new 남발 => 관리힘들다(&누수))
	private final int ROW = 10;
	
	public static BoardDAO newInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public void getConnection() {
		try {
			//탐색기를 연다
			Context init = new InitialContext();
			//탐색기에서 c드라이브 찾기
			Context c = (Context)init.lookup("java://comp/env");
			//c드라이브에서 db 정보 가져오기
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		}catch (Exception e) {
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
	
	//기능
	/*
try {
	getConnection();
}
catch(Exception e) {
	e.printStackTrace();
finally {
	disConnection();
}
	*/
	//목록
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql = "select no,subject,name,TO_CHAR(regdate,'yyyy-MM-dd'),hit, group_tab "
					+ "from jspReplyBoard "
					+ "order by group_id desc, group_step asc "
					+ "offset ? rows fetch next ? rows only";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page*ROW)-ROW);
			ps.setInt(2, ROW);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
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
	
	public int boardRowCount() {
		int total=0;
		try {
			getConnection();
			String sql = "select count(*) from jspReplyBoard";
			ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}

	
	//게시물추가
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql = "insert into jspReplyBoard(no,name,subject,content,pwd,group_id) "
					+ "values(jrb_no_seq.nextval, ?,?,?,?,"
					+ "(select nvl(max(group_id)+1,1) from jspReplyBoard))";
			//join => table+table => 필요한 데이터 추출
			//subquery => sql+sql => 한개의 sql만들기
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	
	//상세보기
	public BoardVO boardDetail(int no) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql = "update jspReplyBoard set "
					+ "hit=hit+1 "
					+ "where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql = "select no,subject,name,TO_CHAR(regdate,'yyyy-MM-dd hh24:mi:ss'),content,hit "
					+ "from jspReplyBoard "
					+ "where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setDbday(rs.getString(4));
			vo.setContent(rs.getString(5));
			vo.setHit(rs.getInt(6));
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	//수정하기
	public BoardVO boardUpdateData(int no) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql = "select no,subject,name,content "
					+ "from jspReplyBoard "
					+ "where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo;
	}
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck = false;
		try {
			getConnection();
			String sql = "select pwd from jspReplyBoard "
					+ "where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				bCheck=true;
				
				//수정
				sql = "update jspReplyBoard set "
						+ "name=?, subject=?, content=? "
						+ "where no =?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return bCheck;
	}
	
	//--------------------------------트랜잭션 처리(일괄처리) => insert update delete가 있을 경우 트랜잭션 걸기
	//답변올리기 --일괄처리 프로그램 동시커밋 / 동시 롤백
	//비절차언어 => 에러 무시 => 다음 문장을 수행 => 한번에 처리 => 트랜잭션
	//위치지정 => savepoint
	public void boardReply(int pno, BoardVO vo) {
		try {
			getConnection();
			conn.setAutoCommit(false);
			//답변 대상의 정보읽어오기
			String sql = "select group_id, group_step, group_tab "
					+ "from jspReplyBoard "
					+ "where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int gi = rs.getInt(1);
			int gs = rs.getInt(2);
			int gt = rs.getInt(3);
			rs.close();
			//답변 핵심 - 답변그룹들이 섞이지않도록 기존의 group step + => 아래로 내리기
			sql = "update jspReplyBoard set "
					+ "group_step=group_step+1 "
					+ "where group_id=? and group_step>?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2,gs);
			ps.executeUpdate();
			
			//insert
			sql = "insert into jspReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
					+ "values(jrb_no_seq.nextval, ?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);;
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi);
			ps.setInt(6,gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, pno);
			ps.executeUpdate();
			
			//update
			sql = "";
			//
			conn.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		finally {
			try {
				conn.setAutoCommit(true);
			}catch(Exception ex) {
				
			}
			disConnection();
		}
	}
	
	//삭제하기
	}

