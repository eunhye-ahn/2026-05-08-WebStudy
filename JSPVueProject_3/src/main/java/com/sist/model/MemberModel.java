package com.sist.model;

import com.sist.commons.Commons;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberModel {
	@RequestMapping("member/login.do")
	public void member_login(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberVO vo = MemberDAO.isLogin(id, pwd);
		if(vo.getMsg().equals("OK")) {
			//세션저장
			HttpSession session= request.getSession();
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("isadmin", vo.getIsadmin());
		}
		
		Commons.sendDate(response, "text/html", vo.getMsg());
	}
	@RequestMapping("member/logout.do")
	public void member_logout(HttpServletRequest request, HttpServletResponse response) {
		//세션삭제
		
		
	}
}
