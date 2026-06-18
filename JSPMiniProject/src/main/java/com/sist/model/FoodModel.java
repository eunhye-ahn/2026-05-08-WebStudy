package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	/*
	 * 메소드 => 형식 3가지
	 * 
	 *  Ajax				void
	 *  SendRedirect()		String "redirect:../list.do"
	 *  Forward()			String
	 */
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		final int ROWSIZE=12;
		int start = (curpage*ROWSIZE)-ROWSIZE;
		List<FoodVO> list = FoodDAO.foodListData(start);
		int totalpage = FoodDAO.foodTotalPage();
		
		final int BLOCK =10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage > totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("main_jsp", "../food/list.jsp");
		
		return "../main/main.jsp";
	}
	@RequestMapping("food/detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		Cookie cookie = new Cookie("food_"+no, no);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		//브라우저로 전송
		response.addCookie(cookie);
		//sendRedirect() : 화면이동 (request를 초기화)
		return "redirect:../food/detail.do?no="+no;
	}
	//어떨때는 redirect
	//받아서 출력하면 forward
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		String[] addrs = vo.getAddress().split(" ");
		List<FoodVO> list = FoodDAO.foodRearData(addrs[2]);
		
		request.setAttribute("vo", vo);
		request.setAttribute("list", vo);
		request.setAttribute("main_jsp", "../food/detail.jsp");
		return "../main/main.jsp";
	}
}
