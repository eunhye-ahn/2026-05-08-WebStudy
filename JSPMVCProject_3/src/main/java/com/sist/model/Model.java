package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Spring / struts 
 * 			|action=>execute
 * |controller=>handler
 */

public interface Model {
	//사용자요청 시 처리하는 메서드
	public String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
