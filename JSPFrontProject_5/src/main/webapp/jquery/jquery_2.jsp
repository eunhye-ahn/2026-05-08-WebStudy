<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
li:hover{
	cursor:pointer;
}
img:hover{
	opacity:0.3
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	/*
	$('img').hover(function(){
		$(this).css({
			'opacity':0.3,
			'cursor':'pointer',
			'border':'2px solid green'
		})
	},function(){
		$(this).css({
			'opacity':1,
			'cursor':'none',
			'border':'none'
		})
	})
	*/
	$('li').on('click',function(){
		let data = $(this).text()
		alert('선택된 과정:'+data)
		$(this).text(data+'과정')
	})
	
	$('#readBtn').on('click',function(){
		let msg = $('#msg').val()
		let sel = $('#sel').val()
		let t = $('textarea').val()
		
		alert("text:"+msg+"\nselect:"+sel+"\nt:"+t)
	})
	
	$('#writeBtn').on('click',function(){
		$('#msg').val('hello spring')
		$('#sel').val('fe')
		$('textarea').val('안녕')
	})
	$('#htmlBtn').on('click',function(){
		$('#html').html(
			'<font color="red">hello html</font>'
		)
	})
	/*
		click/hover/keyup/change => 가장많이 사용되는 이벤트
		
		val() text() html() prop() append() attr()
		
		여러개 중에 선택된 태그를 가져오는 경우 : $(this)
		
		서버와 연동 => 화면 변경 없이 => 데이터만 변경 : 동적페이지
		-------------------------------------------------
		검색/댓글/페이징/로그인/실시간채팅/실시간 데이터읽기
	*/
	
})
</script>
</head>
<body>
<h1>이미지 스타일 변경</h1>
<img src="m3.jpg" width=150px height=200px>
<img src="m4.jpg" width=150px height=200px>
<h1>this</h1>
<ul>
	<li>자바 : jsp / jdbc / spring / spring-boot</li>
	<li>오라클 : 데이터저장공간(crud, join, subquery, index)</li>
	<li>HTML/CSS : (브라우저 화면 UI)</li>
	<li>javascript: (브라우저 동적 페이지 : 클라이언트용)</li>
	<li>라이브러리 : jquery3,jquery4 (ajax를 이용해 서버연동)</li>
	<li>MVC를 이용한 세미 프로젝트</li>
	<li>spring framework :MVC라이브러리를 갖고있다 : 지원하지않음 (유지보수 용)</li>
	<li>우분투 연습 : host방법 : AWS 이용</li>
	<li>spring boot : jsp</li>
	<li>spring boot : tymeleaf</li>
	<li>CI/CD : git actions, docker, docker-compose</li>
	<li>CI/CD : Jenkis / 쿠버네티스</li>
	<li>최종프로젝트 : spring boot, tymeleaf 기반, vueJS연결</li>
	<li>CI/CD : 무중단</li>
	<li>AWS에서 작업</li>
	<li>1. MYSQL</li>
	<li>2. JPA</li>
	<li>3. NodeJS</li>
	<li>4. TypeScript</li>
	<li>5. React/Redux</li>
	<li>6. TanstackQuery = 개인</li>
</ul>
<h1>val() /입력창:input,textarea,select</h1>
<input type="button" value="읽기" id="readBtn">
<input type="button" value="쓰기" id="writeBtn">
<input type="button" value="html" id="htmlBtn"><br>
<input type="text" id="msg" size=20 value="javascript"><br>
<select id="sel">
	<option>be</option>
	<option>fe</option>
	<option>db</option>
</select><br>
<textarea rows="3" cols="20">hello</textarea><br>
<span id="html"></span>
</body>
</html>