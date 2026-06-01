package com.sist.model;

import java.util.List;

import com.sist.dao.DataBoardDAO;
import com.sist.vo.DataBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListModel implements Model {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) {
		String strpage = request.getParameter("page");
		if(strpage == null){
			strpage = "1";
		}
		int curpage = Integer.parseInt(strpage);
		int start = (curpage*10)-10;
		List<DataBoardVO> list = DataBoardDAO.databoardListData(start);
		int totalpage = DataBoardDAO.databoardTotalPage();
		
		//요청값 담기
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		return "list.jsp";
	}

}
