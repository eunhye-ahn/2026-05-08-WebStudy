package com.sist.model;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardVO;

import jakarta.servlet.http.*;

public class BoardModel {
	public void boardListData(HttpServletRequest request) {
		//1.사용자 요청 정보
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		//현재페이지
		int curpage = Integer.parseInt(strpage);
		//db연동
		BoardDAO dao = BoardDAO.newInstance();
		List<BoardVO> list = dao.boardListData(curpage);
		int totalpage = dao.boardTotalPage();
		
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
	}
	public void boardInsert(HttpServletRequest request, HttpServletResponse response) {
		 String name=request.getParameter("name");
		 String subject=request.getParameter("subject");
		 String content=request.getParameter("content");
		 String pwd=request.getParameter("pwd");
		 BoardVO vo = new BoardVO();
		 vo.setName(name);
		 vo.setSubject(subject);
		 vo.setContent(content);
		 vo.setPwd(pwd);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String today = sdf.format(new Date());
		 request.setAttribute("today", today);
		 
		 BoardDAO dao = BoardDAO.newInstance();
		 dao.boardInsert(vo);
		 
		 try {
		 response.sendRedirect("list.jsp");
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	}
	
	public void boardDetail(HttpServletRequest request) {
		String no = request.getParameter("no");
		
		BoardDAO dao = BoardDAO.newInstance();

	    BoardVO vo = dao.boardDetail(Integer.parseInt(no));

		request.setAttribute("vo", vo);
	}
}
