package com.sist.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.MemberDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.MemberVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		List<FoodVO> list = FoodDAO.foodListData((curpage*12)-12);
		int totalpage = FoodDAO.foodTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		//데이터를 보낸다 => food/list.jsp
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		//include되는 파일명 지정
		request.setAttribute("main_jsp", "../food/list.jsp");
		
		//쿠키읽기
		Cookie[] cookies = request.getCookies();
		List<FoodVO> cList = new ArrayList<FoodVO>();
		int j=0;
		if(cookies != null) {
			//최신쿠키부터 읽어오기
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("food_")) {
					//getName() => 키 읽기
					if(j>=9) break;
					String no = cookies[i].getValue();	//=>저장값가져오기
					FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
					cList.add(vo);
					j++;
				}
			}
		}
		
		request.setAttribute("cList", cList);
		request.setAttribute("size", cList.size());
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/login.do")
	public void member_login(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberVO vo = MemberDAO.memberLogin(id, pwd); 
		if(vo.getMsg().equals("OK")) {
			//세션저장
			HttpSession session = request.getSession();
			session.setAttribute("id", vo.getId());
			session.setAttribute("pwd", vo.getPwd());
			session.setAttribute("name", vo.getName());
		}
		
		//jsp로전송
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(vo.getMsg());
		}catch(Exception e) {}
	}
	@RequestMapping("member/logout.do")
	public void member_logout(HttpServletRequest request, HttpServletResponse response) {
		//세션해제
		HttpSession session = request.getSession();
		session.invalidate();	//모든메모리해제
		
		//request 존재 => session과 cookie 제어 가능
		
		try {
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("yes");
		}catch(Exception e) {}
	}
}
