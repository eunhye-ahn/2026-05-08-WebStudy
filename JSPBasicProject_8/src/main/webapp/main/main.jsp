<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//	session.setAttribute("id", "hong");
//	session.setAttribute("name", "eunhye");
	String id = (String)session.getAttribute("id");
	//로그인 => 로그인처리 => 세션에 사용자의 일부정보를 서버에 저장
	//프로젝트 전체에서 공통을 사용(전역변수)
	//데이터유지방법 => 세션/쿠키
	/*
		Cookie => 내 컴퓨터에 저장 => 영구성
		Session => 서버에 저장 => 브라우저 종료 / 로그아웃시 사라진다
		=> 사용자당 1개만 생성
		
		1. 필요한 이유
			- HTTP는 원래 구조가 기억을 못하는 구조
			
	----------------------------------------------------------
	쿠키생성
	Cookie cookie = new Cookie(String key, String value)
								---------- key가 중복되면 저장이 안된다 => Map방식
	=> 기간 설정 
	cookie.setMaxAge(초단위) => 60*60*24 => 24시간
	=> 브라우저로 전송
	response.addCookie(cookie)
	=>원하는 위치에 저장
	cookie.setPath("/")
	
	쿠키읽기
	Cookie[] cookies = request.getCookies()
	=> key 활용(구분자역할)
	=> 맛집 food_맛집번호 / 상품 저장 goods_상품번호
	=> 		id_food_맛집번호 : 유저아이디에 해당하는 사람만 조회가능
	for(Cookie c : cookies)
	{
		if(c.getName().startWith("food_")){} //맛집만 조회
	}
	
	쿠키수정
	Cookie cookie = new Cookie("a1","aaa")
	Cookie cookie = new Cookie("a1","bbb") //키를 동일하게 하면 데이터를 덮어쓰기
	
	언제사용하는지
	자동로그인 / 최근방문 / 팝업창제어(하루만보기)
	
	특징
	브라우저에 저장
	보안안좋음
	
	
	----------------------------------------------------------------------
	
	session : 내장 객체 => 미리생성된객체
	=> request를 이용해서 생성가능
	HttpSession session = request.getSession();
	
	세션저장
	session.setAttribute("키", 값)
							  --- Object : ArrayList/VO
					     ---- String
	
	=> 세션에 저장된 데이터 읽기
	session.getAttribute("키")
	
	
	=> 세션에서 일부 삭제
	session.removeAttribute("키")
	
	=>세션에서 전체삭제 : 로그아웃
	session.invalidate()
	
	=>기간 설정
	session.setMaxactiveInterval(초단위)
					===> 기본값 : 30분
				
	=>생성된 시점
	session.getCreationTime() : 세션에 등록된 시간
	session.getId() : 각 클라이언트당 1개 배정
	----------------채팅
	session.isNew() : 처음으로 등록된 상태
	----------------장바구니
			
	특징
	1.서버에저장
	2.보안좋음
	3.브라우저종료시 자동삭제
	4.사용자마다 개별공간 (따로저장) =>getId()
	
	어디에쓰이는지
	로그인유지(사용자의 일부정보 저장)
	
	로그인 ------ 세션
	자동로그인 ----- 쿠키 spring:remember-me
	장바구니 -------- session / database
	보안정보/security ------ session
	=> jwt : cookie 기반 => 소셜로그인 (cookie)
	
	가벼운데이터: 쿠키
	안전 필요한 데이터 : 세션
	
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.login{
	width: 960px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
	<div class="row login">
		<%
		if(id== null){
		%>
		<form method="post" action="../member/login.jsp">
			<div class= "logform text-right">
				ID:<input type=text size=15 name=id class="input-sm">
				&nbsp;
				PW:<input type=password size=15 name=pwd class="input-sm">
				&nbsp;
				<button class="btn-sm btn-primary">로그인</button>
			</div>
		</form>
		<%
		} 
		else{
		%>
		<form method="post" action="../member/logout.jsp">
			<div class= "logform text-right">
				<%=session.getAttribute("name") %>님이 로그인 되었습니다
				&nbsp;
				<button class="btn-sm btn-primary">로그아웃</button>
			</div>
		</form>
		<%} %>
	</div>
</div>
</body>
</html>