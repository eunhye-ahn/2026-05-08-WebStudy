package com.sist.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodLikeDAO;
import com.sist.dao.JjimDAO;
import com.sist.dao.ReviewDAO;
import com.sist.vo.FoodLikeVO;
import com.sist.vo.FoodVO;
import com.sist.vo.JjimVO;
import com.sist.vo.ReviewVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("id");
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
		
		
		FoodLikeVO lvo = new FoodLikeVO();
		lvo.setFno(Integer.parseInt(no));
		lvo.setMember_id(memberId);
		boolean fCheck = FoodLikeDAO.foodLikeChecked(lvo);
		System.out.println("fCheck:"+fCheck);
		
		JjimVO jvo = new JjimVO();
		jvo.setFno(Integer.parseInt(no));
		jvo.setId(memberId);
		int jCount = JjimDAO.jjimCheck(jvo);
		
		request.setAttribute("jCount", jCount);
		
		System.out.println(rList);
		System.out.println(rCount);
		
		request.setAttribute("fCheck", fCheck);
		request.setAttribute("rList", rList);
		request.setAttribute("rcount", rCount);
		request.setAttribute("food_jsp", "../food/detail.jsp");
		request.setAttribute("main_jsp", "../food/food_main.jsp");
		return "../main/main.jsp";
	}
	
	
	//jquery
	@RequestMapping("food/find_jquery.do")
	public String food_find(HttpServletRequest request, HttpServletResponse response) {
		
		String strpage = request.getParameter("page");
		String column = request.getParameter("column");
		String fd = request.getParameter("fd");
		
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("column", column);
		map.put("fd", fd);
		
		List<FoodVO> list = FoodDAO.foodFindData(map);
		int totalpage = FoodDAO.foodFindTotalPage(map);
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		request.setAttribute("food_jsp", "../food/find.jsp");
		request.setAttribute("main_jsp", "../food/food_main.jsp");
		
		return "../main/main.jsp";
	}
	
	
	//vue
	@RequestMapping("food/find.do")
	public void food_find_vue(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		String column = request.getParameter("column");	//type,name,address
		String fd = request.getParameter("fd");
		
		if(strpage == null) {
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*12)-12;
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("column", column);
		map.put("fd", fd);
		
		List<FoodVO> list = FoodDAO.foodFindData(map);
		int totalpage = FoodDAO.foodFindTotalPage(map);
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		
		
		try {
			 map = new HashMap();
			map.put("start", start);
			map.put("column", column);
			map.put("list", list);
			map.put("curpage", curpage);
			map.put("totalpage", totalpage);
			map.put("fd", fd);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(map);
			
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
