package com.sist.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void board_list_vue(HttpServletRequest request, HttpServletResponse response){
		String page = request.getParameter("page");
		int curpage = Integer.parseInt(page);
		int start = (curpage*12)-12;
		List<BoardVO> list = BoardDAO.boadListData(start);
		int totalpage = BoardDAO.boadTotalPage();
		
		final int BLOCK = 10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		
		Map map = new HashMap();
		map.put("board_list", list);
		map.put("curpage", curpage);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(map);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("board/detail.do")
	public void board_detail_vue(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		Map map = new HashMap();
		map.put("board_detail", vo);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(map);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("board/insert.do")
	public String board_insert(HttpServletRequest request, HttpServletResponse response) {
		
		return "../board/insert.jsp";
	}
	@RequestMapping("board/insert_ok.do")
	public void board_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		System.out.println(name);
		System.out.println(subject);
		System.out.println(content);
		System.out.println(pwd);
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO.boardInsert(vo);
	}
	@RequestMapping("board/delete_vue.do")
	public void boad_delete(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		String msg = "";
		System.out.println(msg);
		boolean bCheck = BoardDAO.boardDelete(Integer.parseInt(no), pwd);
		if(bCheck) {
			msg="yes";
		}
		else {
			msg="no";
		}
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(msg);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("board/update_vue.do")
	public void board_update(HttpServletRequest request, HttpServletResponse response) {
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
		
		String msg = "no";
		boolean bCheck = BoardDAO.boardUpdate(vo);
		
		if(bCheck) {
			msg="yes";
		}
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(msg);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
