package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.ReviewDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReviewVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	@RequestMapping("food/food_main.do")
	public String food_main(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		List<FoodVO> list = FoodDAO.foodListData(start);
		int totalpage = FoodDAO.foodTotalPage();
		
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
		
		request.setAttribute("food_jsp", "../food/list.jsp");
		request.setAttribute("main_jsp", "../food/food_main.jsp");
		
		//쿠키 읽어오기
		List<FoodVO> cList = new ArrayList<FoodVO>();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					String no = cookies[i].getValue();
					if(no!=null) {
						FoodVO vo = FoodDAO.foodCookieData(Integer.parseInt(no));
						cList.add(vo);
					}
				}
			}
		}
		request.setAttribute("cList", cList);
		
		
		return "../main/main.jsp";
	}
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		
		String no = request.getParameter("no");
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		System.out.println(vo);
		
		request.setAttribute("vo", vo);
		
		Cookie[] cookies = request.getCookies();
		//중복저장방지 => 이미 쿠키에 있다면 만료시키기
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().equals("food_"+no))
				{
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
					break;
				}
			}
		}
		
		//쿠키저장
		Cookie cookie = new Cookie("food_"+no,no);
		//map형식?? 키+값 => 키가 중복저장되면 안된다
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		List<ReviewVO> rList = ReviewDAO.reviewListData(Integer.parseInt(no));
		int rCount = rList.size();
		
		System.out.println(rList);
		System.out.println(rCount);
		
		request.setAttribute("rList", rList);
		request.setAttribute("rcount", rCount);
		request.setAttribute("food_jsp", "../food/detail.jsp");
		request.setAttribute("main_jsp", "../food/food_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("food/food_find.do")
	public String food_find(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("food_jsp", "../food/find.jsp");
		request.setAttribute("main_jsp", "../food/food_main.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_find.do")
	public void food_find_vue(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		String column = request.getParameter("column");	//type,name,address
		String fd = request.getParameter("fd");

	}
}
