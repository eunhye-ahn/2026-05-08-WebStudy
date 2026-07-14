package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.JjimDAO;
import com.sist.vo.JjimVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MypageModel {
	@RequestMapping("mypage/main.do")
	public String mypage_main(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("mypage_jsp","../mypage/mypage_home.jsp");
		request.setAttribute("main_jsp","../mypage/mypage_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("jjim/jjim_list.do")
	public String jjim_list(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		List<JjimVO> list = JjimDAO.jjimListData(id);
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		request.setAttribute("mypage_jsp","../mypage/jjim.jsp");
		request.setAttribute("main_jsp","../mypage/mypage_main.jsp");
		return "../main/main.jsp";
	}
}
