package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MemberModel {
	@RequestMapping("member/login.do")
	public String mebmer_login(HttpServletRequest request, HttpServletResponse response) {
		//db연동
		
		//include
		request.setAttribute("main_jsp", "../member/login.jsp");
		
		
		return "../main/main.jsp";
	}
}
