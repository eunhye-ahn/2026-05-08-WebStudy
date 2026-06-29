package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.*;

//MVC 구조 => 호환성 (윈도우/우분투 => AWS 호스팅) => .jar(라이브러리 생성)
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//클래스 저장 => 모델클래스
	private List<String> clsList = new ArrayList<String>();
	/**
	 * com.sist.model.FoodModel
	 * com.sist.model.MemberModel
	 * 
	 */
	
	public void init(ServletConfig config) throws ServletException {
		try {
			URL url = this.getClass().getClassLoader().getResource("."); //현재폴더 읽기 (경로명 지정 X) => 각 OS에서 실제 경로로 읽어옴(호환성)
			//파일로 변경
			File file = new File(url.toURI());
			//System.out.println(file.getPath()); //경로명 + 파일명 읽어오기
			String path = file.getPath();
			path = path.replace("\\", File.separator); //자동변환 window:\\ 우분투:/ <- File.separator
			
			//=> 우분투 => war => 톰캣에 올려서 실행 *********
			
			/**
			 * C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCLastProject\WEB-INF\classes
			 * 																											  ---------- 제거 (xml파일 경로를 찾기 위해)
			 */
			path = path.substring(0,path.lastIndexOf(File.separator));
			//System.out.println(path);
			path=path+File.separator+"application.xml";
			//System.out.println(path);
			
			//[파싱] xml 안에 있는 데이터 추출 -> com.sist.model 추출
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//파싱기 생성
			DocumentBuilder db = dbf.newDocumentBuilder();
			//파싱할 xml 읽어오기
			Document doc = db.parse(new File(path));
			// root 태그 읽기 => xml (root 태그는 테이블과 같은 역할)
			Element beans = doc.getDocumentElement();
			//System.out.println(beans.getTagName());
			//같은 이름의 태그를 모아서 추출
			NodeList list = doc.getElementsByTagName("context:component-scan");
			String pack = "";
			for(int i=0;i<list.getLength();i++) {
				Element elem = (Element)list.item(i);
				pack = elem.getAttribute("basepakage");
			}
			//System.out.println(pack);
			clsList=ComponentScan.componentScan(file.getPath(), pack);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자요청받기(url)
		// http://localhost
		// /JSPMVCLastProject/main/main.do
		// main/main.do ---실제 사용자가 요청한 메뉴
		String uri = request.getRequestURI();
		String cmd = uri.substring(request.getContextPath().length()+1);
		//System.out.println(uri);
		//System.out.println(cmd);
		
		/**
		 * 어노테이션으로 메서드찾기
		 */
		try {
			//Model클래스 찾기 -> @RequestMapping 찾기
			// => 메서드 호출
			for(String cls : clsList) {
				
				Class clsName = Class.forName(cls);
				if(clsName.isAnnotationPresent(Controller.class) == false) {
					continue;
				}
				//@Controller => Model클래스 
				//메모리할당
				Object obj = clsName.getDeclaredConstructor().newInstance();
				//사용자가 요청한 기능 찾기
				Method[] methods = clsName.getDeclaredMethods();
				//메서드찾기
				for(Method m : methods) {
					if(!m.isAnnotationPresent(RequestMapping.class))
						continue;
					//@RequestMapping
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					
					if(rm.value().equals(cmd)) {
						String jsp = (String)m.invoke(obj, request, response);
						
						if(jsp == null) {
							//js => ajax
							return;
						}else if(jsp.startsWith("redirect:")) {
							//sendRedirect
							jsp=jsp.substring(jsp.indexOf(":")+1);
							response.sendRedirect(jsp);
							//return "redirect:list.do"
						}else {
							//forward
							RequestDispatcher rd = request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						
						return;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
