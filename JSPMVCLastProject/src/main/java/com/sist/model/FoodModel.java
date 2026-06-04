package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		//사용자요청
		String no = request.getParameter("no");
		
		//db연결
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		
		//include
		request.setAttribute("main_jsp", "../food/detail.jsp");
		
		
		return "../main/main.jsp";
	}
}
