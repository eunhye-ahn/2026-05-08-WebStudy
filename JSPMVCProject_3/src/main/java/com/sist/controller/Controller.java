package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sist.model.*;

//DispatcherServlet
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String,Model> clsMap = new HashMap<String,Model>();
	
	public void init(ServletConfig config) throws ServletException {
		// xml => 클래스등록
		try {
			String path = "C:\\webDev\\webStudy\\JSPMVCProject_3\\src\\main\\webapp\\WEB-INF\\application.xml";
			//파싱
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//파서기생성 => html/wml/xml
			//=> 클래스 생성하는 패턴 => factory pattern
			DocumentBuilder db = dbf.newDocumentBuilder();
			//DocumentBuilder 이용해서 파싱
			Document doc = db.parse(new File(path));
			//doc는 xml파싱한 결과 저장
			//Document : 문서저장 공간
			
			//값읽기 - 테이블
			Element root = doc.getDocumentElement(); 
			System.out.println(root.getTagName());
			
			//beans 안 데이터 추출
			NodeList list = root.getElementsByTagName("bean");
			
			//bean태그 안에 있는 id/class 추출
			for(int i=0;i<list.getLength();i++) {
				Element bean = (Element)list.item(i);
				String id = bean.getAttribute("id");
				String cls = bean.getAttribute("class");
				//System.out.println("id: "+id+", "+"class: "+cls);
				Class clsName = Class.forName(cls);
				//리플렉션 메모리할당
				//HandlerMapping
				Model model = (Model)clsName.getDeclaredConstructor().newInstance();
				clsMap.put(id, model);
				System.out.println("id: "+id+"class: "+model);
			}
		}catch(Exception e) {
			
		}
	}

	//DispatcherServlet -모든요청을 받아서처리
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//브라우처 요청 url 추출
			// ex) /JSPMVCProject_3/main/main.do → "main/main.do"
			String uri = request.getRequestURI();
			String key = uri.substring(request.getContextPath().length()+1);
			
			// 2. HandlerMapping - URL에 맞는 Model(Controller) 찾기
			Model model = clsMap.get(key);
			
			// 3. Controller - 비즈니스 로직 실행 후 JSP 경로 반환
			String jsp=model.handleRequest(request, response);
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			
			// 4. ViewResolver - JSP 경로로 포워딩
			rd.forward(request, response);
		}catch(Exception e) {
			
		}
	}
}
