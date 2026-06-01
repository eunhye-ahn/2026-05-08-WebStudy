package com.sist.model;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailModel implements Model {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) {
		
		//사용자가 보낸 값 받기
		String no = request.getParameter("no");
		
		
		//dao연결
		DataBoardVO vo = DataBoardDAO.databoardDetail(Integer.parseInt(no));
		
		//값넣기
		request.setAttribute("vo", vo);
		
		return "detail.jsp";
	}

}
