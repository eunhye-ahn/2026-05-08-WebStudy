package com.sist.model;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
/**
 * 
 * Controller : 처리된 데이터 jsp로 전송 : servlet
 * 
 * ================================================MV
 * Model : 데이터 처리 : java + 처리된 데이터 jsp로 전송
 * View : JSP => JSTL/EL ==> 화면출력 
 * 				=> Front(JavaScript)
 * 
 */
import java.util.*;
import com.sist.dao.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardModel {
	public void boardListData(HttpServletRequest request) {
		//사용자요청받기
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		//db연결
		BoardDAO dao = BoardDAO.newInstance();
		List<BoardVO> list = dao.boardListData(curpage);
		int count = dao.boardRowCount();
		int totalpage = (int)(Math.ceil(count/10.0));
		count=count-((curpage*10)-10); //순차적 숫자로 출력하기 위해
		
		//가지고온 데이터 jsp로 전송
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		request.setAttribute("msg", "관리자가 삭제한 게시물입니다");
	}
	
	public void boardInsert(HttpServletRequest request, HttpServletResponse response) {
		// => 화면이동-list로 이동 (response 필요)
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO dao = BoardDAO.newInstance();
		dao.boardInsert(vo);
		
		try {
			response.sendRedirect("list.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void boardDetail(HttpServletRequest request) {
		String no = request.getParameter("no");
		
		BoardDAO dao = BoardDAO.newInstance();
		BoardVO vo = dao.boardDetail(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
	}
	
	public void boardUpdateData(HttpServletRequest request) {
		String no = request.getParameter("no");
		BoardDAO dao = BoardDAO.newInstance();
		BoardVO vo = dao.boardUpdateData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
	}
	
	public void boardUpdate(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO dao = BoardDAO.newInstance();
		boolean bCheck = dao.boardUpdate(vo);
		
		try {
			
			if(bCheck == true) {
					response.sendRedirect("detail.jsp?no="+no);
				
			}else {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.write("<script>");
				out.write("alert(\"비밀번호가 틀립니다\");");
				out.write("history.back();");
				out.write("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void boardReply(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("pno");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		int pno = Integer.parseInt(no);
		
		BoardDAO dao = BoardDAO.newInstance();
		dao.boardReply(pno, vo);
		
		try {
			response.sendRedirect("list.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void boardDelete(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		
		BoardDAO dao = BoardDAO.newInstance();
		boolean bCheck = dao.boardDelete(Integer.parseInt(no), pwd);
		
		if(bCheck == true) {
			try {
				response.sendRedirect("list.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.write("<script>");
				out.write("alert(\"비밀번호가 틀립니다\");");
				out.write("history.back();");
				out.write("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
