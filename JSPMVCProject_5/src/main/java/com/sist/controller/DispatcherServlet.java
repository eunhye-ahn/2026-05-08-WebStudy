package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


//uri 주소 마지막 => .do가 있는 경우에 servlet을 호출
//MVC는 무조건 컨트롤러를 거쳐야 수행
/**
 * 브라우저(주소창) 요청 
 * => 컨트롤러(디스패처서블릿)
 * 			- 모델찾기
 * 			- 모델메서드 호출
 * 			  
 * => 모델 
 * 		-DAO연동: JDBC/DBCP => MyBatis/JPA
 * 		-결과값
 * 			| 전송: request,session
 * 		-컨트롤러 호출
 * => 컨트롤러 
 * 			| 전송: request,session
 * 		-jsp찾아서 전송
 * 
 * 
 * --------------------------------------------------위를 위해 공부할 내용
 * 1)클래스 구분 => 메서드 찾기 (어노테이션)
 * 							|기능은 X => 검색(인덱스) 역할 => 쉽게찾기
 * 2)필요한 데이터 등록 => XML
 * 3)db연동	=> myBatis / JPA
 * 4)MVC동작방법
 * ----------------------라이브러리 : spring framework, spring boot
 * 
 * 어노테이션
 * => 제작
 * 1.Retention : 메모리 할당시 => 언제까지 사용되는
 * SOURCE / CLASS / RUNTIME
 * --------------	--------프로그램 종료시까지 유지
 * 컴파일 시 바로 사라진다
 * 
 * 2. Target: 어디에 붙일건지
 * **TYPE : 클래스 위(클래스찾기)
 * CONSTRUCTOR : 생성자 위(생성자찾기)
 * **METHOD : 메서드 위(메서드찾기)
 * PARAMETER : 매개변수(매개변수찾기)
 * **FIELD : 멤버변수 위(멤버변수찾기)
 */
//스프링은 @WebServlet이 없다 : web.xml에 등록

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pack = "com.sist.model";	//XML에 등록예정 -xml에 패키지단위로 등록해두면 자동으로 인식되는 시스템
	private List<String> clsList= new ArrayList<String>();
	

	//모델 등록없이 서블릿이 모델 인식하기 init  => spring의 controller 구조
	public void init(ServletConfig config) throws ServletException {
		try {
			//model폴더 경로 => realPath() => 리눅스호환 주의
			String path = "C:\\webDev\\webStudy\\JSPMVCProject_5\\src\\main\\java";
			String s = path+"\\"+pack.replace(".", "\\");
			System.out.println(s);
			//폴더가져오기
			File dir = new File(s);
			//폴더 안 파일 가져오기
			File[] files = dir.listFiles();
			//확장자 자바인 것 가져오기
			for(File file : files) {
				if(file.isFile()) {
					String name = file.getName();
					String ext = name.substring(name.lastIndexOf(".")+1);
					//확장자(자바만 가져오기), 설정파일제외 -모델 등록없이 가져올 수 있다
					if(ext.equals("java")) {
						String ss = pack+"."+name.substring(0,name.lastIndexOf("."));
						//메모리할당
						/*Class clsName = Class.forName(ss);
						Object obj = clsName.getDeclaredConstructor().newInstance();
						System.out.println(obj);*/
						clsList.add(ss);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 요청 받기
		//http://localhost/JSPMVCProject_5/food/list.jsp
		//					-----------------------------uri
		//-----------------------------------------------url
		//					--------------contextPath
		String uri = request.getRequestURI();
		String cmd = uri.substring(request.getContextPath().length()+1);
		try {
			//com.sist.model.FoodModel
			//메모리할당
			for(String cls: clsList) {
				Class clsName = Class.forName(cls);
				if(clsName.isAnnotationPresent(Controller.class)==false) {
					continue;
				}
				//컨트롤러가 있는 클래스는 메모리할당
				Object obj = clsName.getDeclaredConstructor().newInstance();
				//클래스 안의 전체메서드 가져오기
				Method[] methods = clsName.getDeclaredMethods();
				for(Method method: methods) {
					//메서드 위의 requestMapping 가져오기
					RequestMapping rm = method.getAnnotation(RequestMapping.class);
					if(rm.value().equals(cmd)) {
						//jsp받기
						String jsp = (String)method.invoke(obj, request, response);
						if(jsp == null) {
							//void => jquery / vue.js => JSON 전송 //메서드 자체에서 처리
							return;
						}else if(jsp.startsWith("redirect:")) {
							//sendRedirect(request초기화 후 화면 변경)
							//_ok
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}else {
							//forward(request 유지하면서 화면 변경)
							RequestDispatcher rd = request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
