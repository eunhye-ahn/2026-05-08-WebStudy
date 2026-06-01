package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import com.sist.model.*;

/**
 * 
 */

@WebServlet("*.do")
//고정이 안되면 사이트는 동작할 수 없다 => 모델을 파일화하기위해 xml로 만듦
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//클래스 등록
	private String[] cls = {
			"com.sist.model.ListModel",
			"com.sist.model.InsertModel",
			"com.sist.model.DetailModel",
			"com.sist.model.DeleteModel"
	};
	private String[] keys = {
			"databoard/list.do",
			"databoard/insert.do",
			"databoard/detail.do",
			"databoard/delete.do"
	};
	private Map<String,Model> clsMap = new HashMap<String, Model>();
	//<bean id="databoard/list.do" class="com.sist.model.ListModel">
	
	//초기화 : 메뉴를 저장 (xml 설정파일 읽기)
	public void init(ServletConfig config) throws ServletException {
		try {
			for(int i =0;i<cls.length;i++) {
				//클래스 메모리할당
				Class clsName = Class.forName(cls[i]);
				Model model = (Model)clsName.getDeclaredConstructor().newInstance();
				clsMap.put(keys[i], model);
				//리플렉션 : 클래스 이름으로 메모리할당 / 멤버변수 설정 / 생성자 호출 / 메소드 호출
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//요청시에 요청을 받고 => 해당 모델 찾기 => 처리결과값을 jsp로 전송
	//요청처리는 model
	//doGet(): GET / doPost(): POST => service(GET/POST 통합 처리)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//http://localhost/JSPMVCProject_4/databoard/list.do
			
			//요청받기
			String uri = request.getRequestURI();
			String key = uri.substring(request.getContextPath().length()+1);
			System.out.println(key);
			
			//모델찾기
			Model model = clsMap.get(key);
			
			//메소드 호출 후 jsp받기
			String jsp = model.requestHandler(request, response);
			
			/**
			 * 이동 => request를 유지하지않고 이동 => sendRedirect
			 * 
			 */
			if(jsp.startsWith("redirect:"))
			{
				
				response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
			}else {
				//jsp => request 전송
				RequestDispatcher rd = request.getRequestDispatcher(jsp);
				rd.forward(request, response);
			}
		}catch(Exception e) {}
	}

}
