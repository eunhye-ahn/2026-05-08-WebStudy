package com.sist.model;

import com.sist.controller.Controller;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainModel {
	//1.목록출력
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		//사용자가 보낸 데이터 받기
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		
		//db연동
		List<FoodVO> list = FoodDAO.foodListData(start);
		int totalpage = FoodDAO.foodTotalPage();
		
		//블록별 페이지처리
		final int BLOCK = 10;
		int startpage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endpage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) {
			endpage = totalpage;
		}
		
		//view에 출력할 데이터 담기
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("startpage", startpage);
		
		//include하는 jsp확인
		request.setAttribute("main_jsp", "../main/home.jsp");
		
		//실제 화면 출력할 부분
		return "../main/main.jsp";
	}
}
