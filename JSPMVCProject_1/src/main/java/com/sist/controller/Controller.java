package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

import com.sist.model.DeleteModel;
import com.sist.model.DetailModel;
import com.sist.model.InsertModel;
import com.sist.model.ListModel;
import com.sist.model.UpdateModel;

/**
 * 서블릿 동작 방식
 * init() : 환경설정 : web.xml
 * 	|
 * service() : 
 * 	| --------------------> doGet() : GET, doPost() : POST
 *  |							| sendRedirect <a> location.href
 *  											| form ajax vue react
 *  														--------- 
 *  														axios/fetch => header
 *  														axios.get() axios.post()
 *  			doGet() + doPost() = service
 *  				|		|
 *  				--------- 
 *  					| controller 처리방식이 동일 => 찾기+보내기				
 * destroy() : 메모리해제
 * 
 */
/**
 * 브라우저(jsp) -> controller(servlet) -> 해당 model 찾기 - 처리된 결과 가져오기 - jsp에 전송 
 * 																		--------- 공통 : session에 담기
 * 																		jsp마다 처리 : request에 담아서 
 * 
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException {
		//model 클래스 메모리할당
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청받기
		//http://localhost/JSPMVCProject_1/Controller?cmd=list
		String cmd = request.getParameter("cmd");
		if(cmd == null) {
			cmd="list";
		}
		String jsp = "";
		if(cmd.equals("list")) {
			//요청에 해당하는 모델클래스 찾기
			ListModel model = new ListModel();
			//요청처리(모델의 메서드 호출)
			model.execute(request);
			//jsp찾기
			jsp="view/list.jsp";
		}else if(cmd.equals("detail")) {
			DetailModel model = new DetailModel();
			model.execute(request);
			jsp="view/detail.jsp";
		}else if(cmd.equals("update")) {
			UpdateModel model = new UpdateModel();
			model.execute(request);
			jsp="view/update.jsp";
		}else if(cmd.equals("insert")) {
			InsertModel model = new InsertModel();
			model.execute(request);
			jsp="view/insert.jsp";
		}else if(cmd.equals("delete")) {
			DeleteModel model = new DeleteModel();
			model.execute(request);
			jsp="view/delete.jsp";
		}
		
		//request나 session에 결과값 담아서 전송
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
		
	}

}
