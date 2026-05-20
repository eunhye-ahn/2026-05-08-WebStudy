package com.sist.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 
 * [jsp는 곧 servlet이다]
 * 
 * 서블릿
 * : 순수한 자바중심(로직중심)의 웹 프로그램 
 * 단점
 * - 수정시마다 컴파일을 해서 톰캣 위에 올려준다 => 수정 후 바로 확인할 수 없다
 * - 소스가 길어진다/CSS나 JS처리가 어렵다
 * 장점
 * - 소스가 노출되지 않는다 => 보안이 뛰어나다 .class
 * - 자바중심이기 때문에 호환성/유지보수가 좋다 => 확장성이 좋다
 * 
 * : HTML 중심 => 화면출력
 * 장점
 * - 서블릿의 단점을 보완
 * - 수정 후에 바로 수행 => 컴파일 (톰캣에 의해)
 * - 소스가 짧아짐 => 하지만 자바와 HTML이 구분되어야함
 * - HTML 중심 => CSS/JS 바로 사용가능하다
 * 단점
 * - 소스가 노출된다
 * - 확장성이 안좋다
 * 
 * 보안 / 로직  => 서블릿
 * 화면	 	  => JSP
 * ------------------------mvc
 * 
 * 서블릿 생명주기
 * init() 	-		 
 * 		초기화(생성자대체)		
 * 		=> db연결준비/설정파일(web.xml)
 * 		=> 공통자원초기화 -멤버변수,메소드
 * 
 * 
 * service()		 -		
 * 		클라이언트 요청시마다 실행 => 화면 출력
 * 		=> doGet() => 요청시에 GET방식인 경우		:화면출력
 * 		=> doPost() => 요청시에 POST방식인 경우	:사용자요청처리
 * 		=> service() => GET / POST를 동시에 처리 -요청시에는 한개만 수행
 * 		GET : URL뒤에 데이터 전송 (요청)	?키=값
 * 		POST : 내부적으로 데이터 감춰서 전송 -보안,데이터전송이 많은 경우/파일업로드 
 * 		<form> : GET/POST 설정가능
 * 		Ajax/Vue/React => JS변경
 * 
 * 
 * destroy() : 서버종료/새로고침/화면이동 => 자동으로 서블릿 제거
 * 			=> DB연결 해제 / 메모리 해제 / 로그저장
 * 
 * 
 * 
 * 클라이언트 요청
 * 서블릿 객체생성
 * init() 호출 => 1번만 수행
 * 요청시마다 => service()
 * 응답처리
 * 서버 종료시 => destroy() 호출
 */
@WebServlet("/EmpServlet") //구분자 사용자에 따라 변경 가능
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see jakarta.servlet.http.HttpServlet#jakarta.servlet.http.HttpServlet()
     */
    public EmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init호출");
	}
	
	public void destroy() {
		System.out.println("메모리 해제");
	}

	/**
	 * @see EmpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//실행 후 변환
		/**
		 * HTMl -> text/html
		 * XML  -> text/xml
		 * JSON -> text/plain
		 * 
		 *
		 */
		//응답 타입설정
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//out 영역에 html 저장하면 => 해당 브라우저에서 읽어간다
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>Hello Servlet</h1>");
		out.write("</body>");
		out.write("</html>");
	}

	/**
	 * @see EmpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
