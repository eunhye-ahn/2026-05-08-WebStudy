package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

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
		return "../main/main.jsp";
	}
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		
		String no = request.getParameter("no");
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		System.out.println(vo);
		
		request.setAttribute("vo", vo);
		
		request.setAttribute("food_jsp", "../food/detail.jsp");
		request.setAttribute("main_jsp", "../food/food_main.jsp");
		return "../main/main.jsp";
	}
}
