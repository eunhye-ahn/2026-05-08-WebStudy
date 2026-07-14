package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.NoticeDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.NoticeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeModel {
	@RequestMapping("adminpage/insert.do")
	public String admin_insert(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("admin_jsp", "../notice/insert.jsp");
		return "../adminpage/admin_main.jsp";
	}
	
	@RequestMapping("adminpage/list.do")
	public String admin_list(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		List<NoticeVO> nList = NoticeDAO.noticeListData(start);
		int totalpage = NoticeDAO.noticeTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("nList", nList);
		request.setAttribute("curpage", curpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalpage", totalpage);
		
		
		request.setAttribute("admin_jsp", "../notice/admin_list.jsp");
		return "../adminpage/admin_main.jsp";
	}
	
	@RequestMapping("notice/insert_ok.do")
	public String admin_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		//HttpSession session = request.getSession();
		
		String type = request.getParameter("type");
		System.out.println(type);
		//String name = (String)session.getAttribute("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setType(Integer.parseInt(type));
		vo.setSubject(subject);
		vo.setContent(content);
		
		NoticeDAO.noticeInsert(vo);
		
		request.setAttribute("admin_jsp", "../notice/admin_list.jsp");
		return "redirect:../adminpage/list.do";
	}
	
	
	@RequestMapping("notice/user_list.do")
	public String user_list(HttpServletRequest request, HttpServletResponse response) {
		
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		List<NoticeVO> nList = NoticeDAO.noticeListData(start);
		int totalpage = NoticeDAO.noticeTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("nList", nList);
		System.out.println(nList);
		request.setAttribute("curpage", curpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalpage", totalpage);
		
		
		request.setAttribute("main_jsp", "../notice/user_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("notice/detail.do")
	public String notice_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		NoticeVO vo = NoticeDAO.noticeDetail(Integer.parseInt(no));
		
		
		request.setAttribute("vo", vo);
		
		request.setAttribute("main_jsp", "../notice/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("notice/update.do")
	public String notice_update(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		NoticeVO vo = NoticeDAO.noticeDetail(Integer.parseInt(no));
		
		request.setAttribute("admin_jsp", "../notice/update.jsp");
		request.setAttribute("vo", vo);
		
		return "../adminpage/admin_main.jsp";
	}
	
	@RequestMapping("notice/update_ok.do")
	public String notice_update_ok(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String type = request.getParameter("type");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setNo(Integer.parseInt(no));
		vo.setType(Integer.parseInt(type));
		vo.setSubject(subject);
		vo.setContent(content);
		System.out.println(vo);
		
		NoticeDAO.noticeUpdate(vo);
		
		return "redirect:../adminpage/list.do";
	}
}
