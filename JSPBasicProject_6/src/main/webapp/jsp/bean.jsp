<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. Cookie / Session 사용법
	2. FileUpload
	3. ConnectionPool
	----------------------------
	JSP (HTML 변경이 가능 => 데이터 변경 (한 파일에서 여러개를 수행 = 동적페이지))
		------화면 출력	  -------- java
		-------------------------------html+java => 복잡하다/분리할수없음(여러명이 동시작업 불가능)
																	-------------협업 => 분리(html/java)
																							---fe ----be
																							------------->mvc
	1. JSP 지시자
	=> 전체 설정을 담당하는 태그
	=> 
	page : JSP 페이지 속성 설정
	****contentType : 브라우저로 응답
				text/html => default
				=>text/plain => JSON (자바 + 자바스크립트 연동)
								------------------------Restful
								한글 => charset=UTF-8 
	****import : 라이브러리 / 사용자 클래스읽기
	errorPage : 오류가 발생하면 이동 페이지
	buffer : HTML을 출력하는 메모리 공간 크기 => 8kb
=> tablib : 자바의 기본 (제어문), String, 날짜변환, 숫자변환
			=> 태그형으로 만들어져 있다 => <% %> 사용하지 않게 만든다
			***<c:forEach> <c:if> <c:choose>
			***<c:redirect> : sendRedirect()
			<fmt:formatDate> <fmt:formatNumber>
							 | => DecimalDateFormat
			| => SimpleDateFormat 
			<fn:substring....>
			=> 이미 제작되어 있다 (JSTL-Java Standard Tag Library)
	------------------------------------------------------------
	JSP 내장객체
	=> 미리 생성된 객체(자동으로 제공하는 객체)
		***request : 사용자가 전송한 데이터를 담은 클래스
		***response : 응답하는 클래스 (HTML, Cookie)
		***session : 사용자당 1개만 생성 => 서버에 저장
					=> Object 단위로 저장이 가능 (ArrayList가능)
					=> 보안이 높다 => 장바구니/로그인
		application : 전체 내용을 관리
		---------------------------------------
		out : 출력 버퍼 관리
		pageContext : <jsp:include> <jsp:forward>
	------------------------------------------------------------
	***Cookie => 클라이언트 브라우저에 저장
				-------------보안이 약하다 / 만료시간을 설정할 수 있다 / 문자열만 저장가능
					=> 최근방문,로그인유지,자동로그인
									  -------- 비밀번호 암호화
	-------------------------------------------------------------
	1. 액션태그
	****<jsp:include>
	--------------------------------------------------Model 1
	<jsp:forward>
	<jsp:useBean>
	<jsp:setProperty> <jsp:getProperty> <jsp:param>
	2. 자바빈즈
		----- VO/DTO
		=> 데이터롤 모아서 전송 : JSP => Beans
		=> 캡슐화 : 데이터보호
		=> ---- 데이터은닉화/변수에 기능부여 : 읽기/쓰기
							------- 메소드 : getter/setter
				변수 : private
				데이터를 담는 규칙
				제작 
					private String name;
				private boolean admin;
				=> 읽기
					public boolean isAdmin();
								   --- get대신 is
	--------------------------------------------------
	MVC(Spring)
		=> Java : Model => 순수자바
		=> HTML : VIew => HTML => 태그 (JSTL/EL)
		=> 연결 : Controller
				=> forward() 메서드 이용
				=> Spring에 이미 만들어져있다
	1. 자바빈
	jsp -- servlet -- db데이터 전송
	캡슐화 : private, setter, getter
	MVC에서도 데이터를 모아서 한번에 전송
	데이터베이스 컬럼과 일치 (desc table명)
----------------------------------------------------
	javabean				dto					vo
	목적 : JSP 표준			데이터전달객체			불변=>고정
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>