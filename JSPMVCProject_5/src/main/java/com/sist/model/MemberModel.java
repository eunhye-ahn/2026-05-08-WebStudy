package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MemberModel {
	//로그인
	@RequestMapping("member/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String msg = "로그인처리";
		request.setAttribute("msg", msg);
		request.setAttribute("main_jsp", "../member/login.jsp");
		return "../main/main.jsp";
	}
}
