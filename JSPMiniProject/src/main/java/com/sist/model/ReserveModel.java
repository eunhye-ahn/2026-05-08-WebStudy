package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
import java.util.*;
import java.text.*;

@Controller
public class ReserveModel {
	@RequestMapping("reserve/diary.do")
	public String reserve_diary(HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		String strYear=request.getParameter("year");
		String strMonth=request.getParameter("month");
		
		String today = sdf.format(date);
		StringTokenizer st = new StringTokenizer(today,"-");
		String sYear = st.nextToken();
		String sMonth = st.nextToken();
		String sDay = st.nextToken();
		
		if(strYear == null) {
			strYear = sYear;
		}
		if(strMonth == null) {
			strMonth = sMonth;
		}
		
		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		int day = Integer.parseInt(sDay);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);		//month 0
		cal.set(Calendar.DATE, 1);
		
		int week = cal.get(Calendar.DAY_OF_WEEK);
		week=week-1;		// week 1
		
		int lastday = cal.getActualMaximum(cal.DATE);
		
		// 데이터 전송
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("today", day);
		request.setAttribute("week", week);
		request.setAttribute("lastday", lastday);
		
		String[] strWeek = {"일","월","화","수","목","금","토"};
		request.setAttribute("strWeek", strWeek);
		
		return "../reserve/diary.jsp";
	}
	
	@RequestMapping("reserve/reserve_food.do")
	public String reserve_food(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		List<FoodVO> list = FoodDAO.foodCategoryData(type);
		request.setAttribute("list", list);
		return "../reserve/reserve_food.jsp";
	}


	@RequestMapping("reserve/reserve_time.do")
	public String reserveTime(HttpServletRequest request, HttpServletResponse response) {
		String time = TimeConfig.reserveTime();
		System.out.println(time);
		request.setAttribute("time", time);
		return "../reserve/reserve_time.jsp";
	}
	
	@RequestMapping("reserve/reserve_inwon.do")
	public String reserve_inwon(HttpServletRequest request, HttpServletResponse response) {
		return "../reserve/reserve_inwon.jsp";
	}
}
