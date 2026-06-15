package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.ReplyDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReplyVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {
	@RequestMapping("food/detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response) {
		
		//방문한 내용 쿠키에 저장
		String no = request.getParameter("no");
		Cookie cookie = new Cookie("food_"+no, no);
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return "redirect:../food/detail.do?no="+no;	//redirect : 리퀘스트 초기화
		//sendRedirect()
	}
	/*
	 * 쿠키는 브라우저에 저장 (보안 취약)	=> 문자열만 저장 가능 (자동로그인, 장바구니 저장, 최근방문)
	 * 세션은 서버에 저장 (보안이 뛰어나다)	=> Object 단위로 저장 가능 (접속자 일부 정보를 저장 시에)
	 * -----------------------------------------------------------------------
	 * request를 이용해서 생성이 가능
	 * Cookie[] cookies = request.getCookies()
	 * HttpSession session = request.getSession()
	 */
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		//값받기
		String no = request.getParameter("no");
		//include
		request.setAttribute("main_jsp", "../food/detail.jsp");
		//dao연결
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		//결과값보내기
		request.setAttribute("vo", vo);
		
		List<ReplyVO> list = ReplyDAO.replyListData(Integer.parseInt(no));
		request.setAttribute("rList", list);
		request.setAttribute("rCount", list.size());
		
		
		return "../main/main.jsp";	//forward : 리퀘스트를 유지
		//forward()
	}
}
