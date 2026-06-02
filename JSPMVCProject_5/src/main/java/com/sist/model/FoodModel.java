package com.sist.model;

import java.util.List;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 브라우저 요청 (uri => food/list.do)
 * -> controller -@Controller 찾아서 메모리할당
 * -> 컨트롤러 클래스 안의 모든 메서드 조회 후 @RequestMapping가져오기
 * -> requestMapping value와 uri(contextpath제거한 uri) 같은 것 찾기
 * ->  method 실행 invoke이용 <- 메서드명몰라도 실행가능
 * -> return값으로 jsp받아와서 실행시키기(request로 값을 채운)
 * -> 경우의수
 * 1. jsp가 null(vue/react 등으로 이동)
 * 2. jsp가 redirect로 시작(request초기화)
 * 3. forward(request유지)
 */
@Controller
public class FoodModel {
	//1.목록출력
	@RequestMapping("main/main.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
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
		
		//view에 출력할 데이터 담기
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		//include하는 jsp확인
		request.setAttribute("main_jsp", "../food/list.jsp");
		//실제 화면 출력할 부분
		return "../main/main.jsp";
	}
	
	//2.상세보기
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		//db연동
		FoodVO vo = FoodDAO.foodDetailData(Integer.parseInt(no));
		
		//출력할 데이터전송
		request.setAttribute("vo", vo);
		
		//include하는 jsp확인
		request.setAttribute("main_jsp", "../food/detail.jsp");
		//실제 화면 출력할 부분
		return "../main/main.jsp";
	}
	
	
	//기타: 좋아요 찜하기 예약하기 추천 근처맛집
}
