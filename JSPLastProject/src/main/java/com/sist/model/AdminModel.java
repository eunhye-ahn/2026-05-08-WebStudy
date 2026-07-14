package com.sist.model;

import java.io.PrintWriter;
import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.AdminDAO;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminModel {
	@RequestMapping("admin/main.do")
	public String admin_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("admin_jsp", "../adminpage/admin_home.jsp");
		return "../adminpage/admin_main.jsp";
	}
	@RequestMapping("adminpage/member_list.do")
	public String admin_member_list(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*10)-10;
		System.out.println(start);  //0
		List<MemberVO> list = AdminDAO.memberListData(start);
		System.out.println(".");
		int totalpage = AdminDAO.memberTotalpage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("admin_jsp", "../adminpage/member_list.jsp");
		return "../adminpage/admin_main.jsp";
	}
	@RequestMapping("adminpage/member_update.do")
	public void member_update(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String grade = request.getParameter("grade");
		System.out.println(id);
		System.out.println(grade);
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setGrade(Integer.parseInt(grade));
		System.out.println(vo);
		
		int count = AdminDAO.membergradeChange(vo);
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(String.valueOf(count));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
