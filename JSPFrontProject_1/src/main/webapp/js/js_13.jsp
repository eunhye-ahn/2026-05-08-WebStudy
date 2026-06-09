ㅎ<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
태그제어 => DOM
내장함수 => (Number, Math, String, Date)
브라우저내장객체
window/location/history/document
---------------jquery/vue/react
HTML태그 / CSS선택자
서버와의 연동 : Ajax, fetch, axios
			------------------- async/await

함수 : function(메서드)
	=> 명령문의 집합 : 명령문 여러개를 모아서 한개의 기능을 생성
		--------- 변수/연산/제어
		함수 / 메서드 
				| 클래스 종속(자바유일)
		| 독립적으로 사용
	=> 기능처리 (로그인 / 목록 / 페이지 / ...) : 브라우저 안에서 요청이 있는 경우
		반복 제거 / 재사용 가능 / 에러처리
	=> 자바스크립트에는 함수가 데이터형으로 인식
						------- 매개변수로 사용 가능 => 콜백함수
		names.map(function(){})
				  ------------- 콜백함수 => 자동으로 호출되는 함수
	=> let a = function(){}
	
	
	----------------------------------------------------
	
함수 제작방법 
	1) 선언적 함수
		function 함수명(매개변수)  	=> 선언부
		{
								=> 구현부
		}
		
		특징 
			리턴형을 기재하지않는다
			매개변수는 변수명만 설정
		ex) 
			function login(id, pw){}
			
	2) 익명 함수 : 함수 안에 함수를 선언할 수 있다 / 매개변수 사용
		let 변수명 = function(){}
		
	3) 화살표 함수 : function과 retrun 제거
		let 변수명 = () => {}
	4) 콜백 함수
		function 함수명(function(){}) => forEach, map ...
--------------------------------------------------
함수의 구성요소
	선언부 : 함수명/매개변수 => 리턴형을 서술하지 않는다
	구현부 : 변수선언/연산처리/제어문
	function aaa(매개변수...)
	{
	
	}
	
	
---------------------------------------------------
	자바 스크립트 처리
	-외부 스크립트 : .js 파일을 만들어서 사용
		여러개의 파일에서 동시에 사용 -공통사용
		소스가 긴 경우
		<script src="자바스크립트 파일명"></script>
	
	-내부스크립트: 한파일에서 작업
		<head>
			<script>
				function aa(){
				}
			</script>
		</head>
	-인라인 스크립트 : 태그 한개제어
		<button onclick="javascript:history.back()"></button>
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//선언적함수
function plus(a,b){
	return a+b
}
//익명의함수
let plus2 = function(a,b){
	return a+b
}
//화살표 함수 => function, return 제거
let plus3 = (a,b)=>{return a+b}
let plus4 = (a,b)=>a+b
/*
window.onload=function(){
	
}
*/
window.onload=()=>{
	let a=10
	let b=20
	
	let c= plus(a,b)
	let d= plus2(a,b)
	let e= plus3(a,b)
	let f= plus4(a,b)
	
	document.write("c="+c+", type="+typeof plus+"<br>")
	document.write("d="+d+", type="+typeof plus2+"<br>")
	document.write("e="+e+", type="+typeof plus3+"<br>")
	document.write("f="+f+", type="+typeof plus4+"<br>")
	
	func(callback)
}
let callback = function(){
	document.write("함수 call...<br>")
}
function func(call){
	for(let i=1;i<=10;i++){
		call()
	}
}
/**
 * setTimer(callback) => 지정된 시간에 호출이가능
 					  => 회원가입 => 축하메세지 => 자동으로 메인이동
 
 	setInterval(callback) => 실시간 뉴스, 날씨읽기
 	
 	이벤트처리 
 	=> 이벤트 등록 : $('태그').click(function(){})
 	=> Ajax 처리 / axios => 서버에서 보낸 데이터 자동 읽어올 수 있게
 */
 //함수안의 기능을 집어넣기 위해서는 익명의 함수를 이용하고
 //그외의 함수들은 선언적함수를 이용한다
 window.onload=()=>{
	 let display =()=>{
		 alert("display call..")
	 }
	 display()
 }
</script>

</head>
<body>

</body>
</html>