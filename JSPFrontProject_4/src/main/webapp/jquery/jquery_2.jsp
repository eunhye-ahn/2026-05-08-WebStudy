<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	문자조작
	=textContent : 태그와 태그 사이 값 주입 (순수한 문자열) => html은 파싱안됨
			=>text()
	=innerHTML : 태그와 태그 사이 html추가 => html파싱됨 
			=>html()
	=appendChild : 태그 사이에 태그 첨부
			=>append()
	
	=> html() vs append()
					=> 여러번 사용가능(추가)
		=> 한번만 사용 가능(덮어쓰기가됨-수정)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	let h1 = document.querySelector('h1')
	let val = h1.textContent
	console.log("vanila:"+val)
	val = $('h1').text()
	console.log("jquery:"+val)
	h1.textContent = "vanila"
	$('h1').text('jquery')
	
	let h2 = document.querySelector('h2')
	h2.innerHTML = '<font color="red">vanila</font>'
	
	$('h2').html('<font color="green">jquery</font>')
	$('h2').html('<font color="blue">jquery2</font>')
	
	alert($('div').html())
})
</script>
</head>
<body>
<h1>hello jquery</h1>
<h2>hello jquery</h2>
<h3>hello jquery</h3>
<h4>hello jquery</h4>
<h5>hello jquery</h5>
<div>
	<h6>div안 h6</h6>
	<h6>div안 h6</h6>
	<h6>div안 h6</h6>
</div>
</body>
</html>