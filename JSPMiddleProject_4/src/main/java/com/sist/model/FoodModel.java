package com.sist.model;

import java.util.List;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

import jakarta.servlet.http.HttpServletRequest;

public class FoodModel {
	public void foodListData(HttpServletRequest request) {
		//요청값받기
		String strpage = request.getParameter("page");
		if(strpage == null){
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);

	//db연결
		FoodDAO dao = FoodDAO.newInstance();
		List<FoodVO> list = dao.foodListData(curpage);
		int totalpage = dao.foodTotalpage();
		
		//블록별
		final int BLOCK = 10;
		int startpage = ((curpage-1)/BLOCK*BLOCK)+1;

		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage){
			endpage=totalpage;
		}
		//request가 매개변수 => jsp가 가지고있는 request에 값을 첨부해서 보내줄 수 있다
		//call by reference : 주소를 넘겨주기 주소에 값을 채우는 방법
		//class나 배열은 원본변경가능
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		//request.getAttribute("list") => ${list}
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
	}
}
