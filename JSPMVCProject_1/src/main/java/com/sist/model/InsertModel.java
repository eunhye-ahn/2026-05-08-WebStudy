package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class InsertModel {
	public void execute(HttpServletRequest request) {
		String msg = "게시판 등록";
		request.setAttribute("msg", msg);
	}
}
