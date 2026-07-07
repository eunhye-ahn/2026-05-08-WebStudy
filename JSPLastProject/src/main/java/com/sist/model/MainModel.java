package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.MainDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.TourVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainModel {
   @RequestMapping("main/main.do")
   public String main_main(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   List<FoodVO> fList = MainDAO.mainFoodTop7();
	   System.out.println(fList);
	   List<TourVO> pList = MainDAO.mainPusanTop4();
	   System.out.println(pList);
	   List<TourVO> jList = MainDAO.mainJejuTop4();
	   System.out.println(jList);
	   
	   request.setAttribute("fList", fList);
	   request.setAttribute("main_jsp", "../main/home.jsp");
	   return  "../main/main.jsp";
   }
   @RequestMapping("main/join.do")
   public String main_join(HttpServletRequest request,
		   HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../member/join.jsp");
	   return  "../main/main.jsp";
   }
}