<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	let(변수) const(상수)
	연산자 : === !=
		=> 데이터형이 다른 경우에는 비교가 불가능
		비교연산자 : 숫자/문자/날짜
		+ : 문자열 가능 / 덧셈
		/ : 정수 / 정수 => 실수
	제어문 : if / if~else
		  반복문
		  for(let i=0;i<10;i++){}
		  for in -인덱스번호가져옴
		  for of -실제데이터값가져옴
		  함수화 시킨 것 => Jquery / vue / react => 배열 / JSON
		  map() => 새로운 배열 생성
		  forEach() => 일반반복문
	
	배열 : 관련된 데이터를 모아서 관리하는 영역 => Object형
			형식)
				[값, 값, ...] => 데이터형이 다를 수 있다
	객체(JSON)
	DOM => HTML 태그제어 (CSS 선택자 사용)
		   ----- DOMScript ====> Jquery3 / Jquery4
		   			|=> VueJS / ReactJS
		   				= Vuex/Pinia
		   						= Redux/TanstackQuery/NextJS
	이벤트 처리
	--------
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <style>
 .conatiner{
 	margin-top: 50px;
 }
 .row{
 	margin: 0 auto;
 	widht: 800px;
 }
 </style>
 <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
 <script type="text/javascript">
window.onload=function(){
	//$(function(){}) : Jquery
	//mounted() : vue
	//componentDidMount() : react => hooks => useEffect()
	//자바의 main과 동일한 함수 => 자동호출되는 함수
	//1. 배열 선언
	let arr = [];
	//서버 연결
	axios.get('js_8.do').then(response=>{
		console.log(response)
		let html = ''
		let data = response.data
		data.map((emp)=>{
			html+='<tr>'
			+'<td>'+emp.empno+'</td>'
			+'<td>'+emp.ename+'</td>'
			+'<td>'+emp.job+'</td>'
			+'<td>'+emp.dbday+'</td>'
			+'<td>'+emp.dname+'</td>'
			+'<td>'+emp.loc+'</td>'
			+'</tr>'
		})
		//DOM
		let tbody = document.querySelector("tbody")
		tbody.innerHTML=html
		/*
		1.document.write()
		2.alert()
		3.innerHTML => 지정된 태그에 값을 첨부
			Jquery => html()
		4.VueJS 태그에서 처리 => 값을 서버에서 받을때 자동으로 html에 적용됨(양방향 통신)
			React HTML을 만들어서 추가 : 단방향 통신
		
		1) 배열 []
		2) 객체 : JSON {}
		3) 함수
		4) DOM => 원하는 태그를 가져온다
		querySelector("tbody") 
		5) 이벤트처리방식
		-------------------------------------
		기타
			= 내장함수 / 브라우저내장함수
			--------------------------라이브러리화 : Jquery
		*/
		
	})
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">사원 목록</h3>
			<table class="table">
				<thead>
					<tr class="danger">
						<th>사번</th>
						<th>이름</th>
						<th>직위</th>
						<th>입사일</th>
						<th>부서명</th>
						<th>근무지</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>