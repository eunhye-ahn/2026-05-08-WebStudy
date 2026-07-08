package com.sist.model;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardModel {
	@RequestMapping("board/list.do")
	public String board_list(HttpServletRequest request,HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		final int ROWSIZE=10;
		int start = (ROWSIZE*curpage)-ROWSIZE;
		List<BoardVO> list = BoardDAO.boardListData(start);
		int count = BoardDAO.boardTotalPage();
		int totalpage = (int)(Math.ceil(count/(double)ROWSIZE));
		//페이지마다 -10
		count=count-((curpage*ROWSIZE)-ROWSIZE);
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("list", list);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count);
		
		request.setAttribute("today", new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		System.out.println(new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		
		request.setAttribute("main_jsp", "../board/list.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("board/insert.do")
	public String board_insert(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("main_jsp", "../board/insert.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("board/insert_ok.do")
	public String board_insert_ok(HttpServletRequest request,HttpServletResponse response) {
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		System.out.println(name);
		System.err.println(subject);
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO.boardInsert(vo);
		
		return "redirect:../board/list.do";
	}
	
	@RequestMapping("board/detail.do")
	public String board_detail(HttpServletRequest request,HttpServletResponse response) {
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		
		
		request.setAttribute("main_jsp", "../board/detail.jsp");
		request.setAttribute("vo", vo);
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("board/delete.do")
	public void board_delete(HttpServletRequest request,HttpServletResponse response) {
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		String msg = "";
		boolean bCheck = BoardDAO.boardDeleteData(Integer.parseInt(no), pwd);
		if(bCheck==true) {
			msg="yes";
		}else {
			msg="no";
		}
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("board/update_ok.do")
	public String board_update_ok(HttpServletRequest request,HttpServletResponse response) {
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		
		request.setAttribute("main_jsp", "../board/update.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("board/update.do")
	public void board_update(HttpServletRequest request,HttpServletResponse response) {
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
		System.out.println("번호:"+no);
		System.out.println("이름:"+name);
		System.out.println("제목:"+subject);
		System.out.println("내용:"+content);
		System.out.println("비번:"+pwd);
		
		
		String msg = "";
		boolean bCheck = BoardDAO.boardUpdate(vo);
		if(bCheck==true) {
			msg="yes";
			
		}else {
			msg="no";
		}
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(msg);
			System.out.println("업데이트:"+msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
