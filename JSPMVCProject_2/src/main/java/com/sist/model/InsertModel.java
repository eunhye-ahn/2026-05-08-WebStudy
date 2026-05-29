package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class InsertModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		String msg = "게시판 등록";
		request.setAttribute("msg", msg);
		return "view/insert.jsp";
	}

}
