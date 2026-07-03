package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.sist.vo.FoodVO;

public class DataDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static DataDAO dao;
	public DataDAO() {
		try {
			//객체생성(메모리할당)
			//ssf=new SqlSessionFactoryBuilder.build(reader)
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//싱글톤 : 같은 객체 사용
	public static DataDAO newInstance() {
		if(dao == null) {
			dao = new DataDAO();
		}
		return dao;
	}
	public void getConnection() {
		try {
			//db연결
			//session.openSession()
			conn = DriverManager.getConnection(URL,"hr","hr");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//session.close()
	public void disConnection() {
		try {
			if(conn!=null) {
				conn.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * /**
 * NO         NOT NULL NUMBER        
CNO                 NUMBER        
PRICE      NOT NULL VARCHAR2(30)  
POSTER              VARCHAR2(260) 
NAME       NOT NULL VARCHAR2(100) 
TYPE       NOT NULL VARCHAR2(200) 
PHONE      NOT NULL VARCHAR2(20)  
ADDRESS    NOT NULL VARCHAR2(500) 
SCORE               NUMBER(2,1)   
THEME      NOT NULL CLOB          
TIME                VARCHAR2(50)  
RESERVE             VARCHAR2(100) 
PARKING    NOT NULL VARCHAR2(50)  
CONTENT    NOT NULL CLOB          
IMAGES              CLOB          
LIKECOUNT           NUMBER        
REPLYCOUNT          NUMBER        
JJIMCOUNT           NUMBER        
HIT                 NUMBER        

 */
	//session.insert("",vo)
	public void foodInsert(FoodVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO food2(no,cno,price,poster,name,type,phone,address,score,theme,time,reserve,parking,content,images)"
					+ "VALUES(food2_no_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getCno());
			ps.setString(2, vo.getPrice());
			ps.setString(3, "https://www.menupan.com"+vo.getPoster());
			ps.setString(4, vo.getName());
			ps.setString(5, vo.getType());
			ps.setString(6, vo.getPhone());
			ps.setString(7, vo.getAddress());
			ps.setDouble(8, vo.getScore());
			ps.setString(9, vo.getTheme());
			ps.setString(10, vo.getTime());
			ps.setString(11, vo.getReserve());
			ps.setString(12, vo.getParking());
			ps.setString(13, vo.getContent());
			ps.setString(14, vo.getImages());
			
			//SqlSession session = ssf.openSession(true)
			//session.insert("foodInsert",vo)
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
}
