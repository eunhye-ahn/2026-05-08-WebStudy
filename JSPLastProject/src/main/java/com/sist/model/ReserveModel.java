package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.NoticeDAO;
import com.sist.dao.ReserveDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.NoticeVO;
import com.sist.vo.ReserveVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.*;
import java.text.*;

/**
 * 		승인 : 관리자
 * 		-----------승인 버튼 => 알림 (stomp)
 * 									|javascript라이브러리(socket)
 * 									|=>없는 경우 (db->로그인시 처리)
 * 	=> CRUD : 데이터베이스 연동
 * 			=> cookie session
 * 			=> mvc 이해
 * 			=> mybatis 사용법
 * 			=> 웹 흐름 (실행 실패했을때 처리 흐름 등)
 * 			=> ajax 사용법 / jquery
 * 			----------------------------JSP(JSTL/EL)
 */

@Controller
public class ReserveModel {
	@RequestMapping("reserve/reserve_main.do")
	public String reserve_main(HttpServletRequest request, HttpServletResponse response) {
		

		request.setAttribute("main_jsp", "../reserve/reserve_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("reserve/reserve_food.do")
	public String reserve_food(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String strpage = request.getParameter("page");
		if(strpage == null) {
			strpage = "1";
		}
		if(type == null) {
			type = "한식";
		}
		System.out.println(type);
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*20)-20;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("type", type);
		List<FoodVO> list = ReserveDAO.reserveFoodListData(map);
		int totalpage = ReserveDAO.reserveFoodTotalpage(type);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		return "../reserve/reserve_food.jsp";
	}
	
	@RequestMapping("reserve/reserve_date.do")
	public String reserve_date(HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		String today = sdf.format(date);
		
		StringTokenizer st = new StringTokenizer(today,"-");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		
		int week = cal.get(Calendar.DAY_OF_WEEK);
		int lastday = cal.getActualMaximum(Calendar.DATE);
		
		
		week = week-1; //0:일요일
		
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("week", week);
		request.setAttribute("lastday", lastday);
		
		String[] weeks = {"일","월","화","수","목","금","토"};
		request.setAttribute("weeks", weeks);
		
		
		return "../reserve/reserve_date.jsp";
	}
	
	@RequestMapping("reserve/reserve_time.do")
	public String reserve_time(HttpServletRequest request, HttpServletResponse response) {
		String[] times = {"10:00","11:00","12:00","13:00","13:30"};
		
		request.setAttribute("times", times);
		
		return "../reserve/reserve_time.jsp";
	}
	
	@RequestMapping("reserve/reserve_inwon.do")
	public String reserve_inwon(HttpServletRequest request, HttpServletResponse response) {
		
		return "../reserve/reserve_inwon.jsp";
	}
	
	@RequestMapping("reserve/reserve_insert.do")
	public String reserve_insert(HttpServletRequest request, HttpServletResponse response) {
		String rno = request.getParameter("rno");
		String rdays = request.getParameter("rdays");
		String rtime = request.getParameter("rtime");
		String rinwon = request.getParameter("rinwon");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		
		ReserveVO vo = new ReserveVO();
		vo.setId(id);
		vo.setName(name);
		vo.setFno(Integer.parseInt(rno));
		vo.setRdate(rdays);
		vo.setRtime(rtime);
		vo.setInwon(rinwon);
		System.out.println(vo);
		
		//db
		ReserveDAO.reserveInsert(vo);
		
		return "redirect:../mypage/mypage_reserve.do";
	}
	@RequestMapping("reserve/reserve_delete.do")
	public String reserve_delete(HttpServletRequest request, HttpServletResponse response) {
		String rno = request.getParameter("rno");
		
		ReserveDAO.reserveCancel(Integer.parseInt(rno));
		
		return "redirect:../mypage/mypage_reserve.do";
	}
}
