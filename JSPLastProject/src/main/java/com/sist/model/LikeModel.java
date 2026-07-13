package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodLikeDAO;
import com.sist.vo.FoodLikeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LikeModel {
	@RequestMapping("like/like_on.do")
	public String food_like_on(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("id");
		
		FoodLikeVO lvo = new FoodLikeVO();
		lvo.setFno(Integer.parseInt(fno));
		lvo.setMember_id(memberId);
		System.out.println(lvo);
		
		FoodLikeDAO.foodLikeInsert(lvo);
		FoodDAO.foodLikeIncrement(Integer.parseInt(fno));
		
		return "redirect:../food/detail.do?no="+fno;
	}
	
	@RequestMapping("like/like_off.do")
	public String food_like_off(HttpServletRequest request, HttpServletResponse response) {
		String fno = request.getParameter("fno");
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("id");
		
		FoodLikeVO lvo = new FoodLikeVO();
		lvo.setFno(Integer.parseInt(fno));
		lvo.setMember_id(memberId);
		System.out.println(lvo);
		
		FoodLikeDAO.foodLikeDelete(lvo);
		FoodDAO.foodLikeDecrement(Integer.parseInt(fno));
		
		return "redirect:../food/detail.do?no="+fno;
	}
}
