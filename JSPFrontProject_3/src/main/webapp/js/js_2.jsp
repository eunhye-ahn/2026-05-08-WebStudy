<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	내장객체
	Number 
		parseInt : 숫자변환
		toLocaleString() => 1,000
	String
		문자열 인덱스 번호 => 0번부터 시작
			=> 자바와 동일
		length() : 문자개수 => 비밀번호 유효성검사 => 입력이 있는지 없는지
		indexOf / lastIndexOf => 문자의 위치찾기
		replace() => 문자변경
		replaceAll() => 정규식 변경
		split() => []
		substring(startIndex,endIndex) : 문자를 자르는 경우
			=> substr(start,count) :오라클에서 파생
		trim() : 좌우공백제거
		startsWith / endsWith / contains(x) => includes
	Array
		push() : 데이터첨부 -마지막
		pop() : 데이터삭제	-마지막
		slice() : 원하는 위치에서 데이터 잘라서 새로운 배열 생성
		length() : 배열개수
		find() : 배열에서 찾기
	Date : 날짜/시간 관리
		let today = new Date()
		let year = today.getFullYear()
		let month = today.getMonth()	=> 0부터시작하므로 +1이 기본
		let date = today.getDate()	//날짜
		let day = today.getDay() //요일
		---------------------------------------fullcalendar : 라이브러리
	Math
		round()
		ceil()
	
	
	브라우저내장객체 BOM
	window
		open() / close()
	location
		href ==> 화면이동 sendRedirect() 역할
	history
		back() / go()
	document : 객체모델 선택자, 화면출력
		write()
		querySelector()
	alert
	
	-----------------------------------------------------------------라이브러리화 => jQuery
		
		
		
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=>{
	/*
	let today = new Date()
	let year = today.getFullYear()
	let month = today.getMonth()+1
	let date = today.getDate()
	let day = today.getDay()
	
	let strDay=["일","월","화","수","목","금","토"]
	
	document.write('<h1>오늘은 '+year+'년 '+month+'월 '+ 
			date+'일 '+strDay[day]+'요일</h1>')
	*/
	
	//1.String
	//split
	let str = "red,blue,yellow,green,blue"
	let colors = str.split(",")
	let html = '<ul>'
	colors.forEach((c)=>{
		
		html+='<li>'+c+'</li>'
	})
	html += '</ul>'
	document.write(html)+
}
</script>
</head>
<body>

</body>
</html>