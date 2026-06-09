<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--

 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload=()=>{
	let first = document.querySelector("div > p")	//자손선택자
	let second = document.querySelector("div span p")	//후손선택자
	first.style.color='red'
	first.addEventListener('click',function(){
		first.style.cursor="pointer"
		alert("first 선택")
	})
	second.style.color='blue'
	second.addEventListener('click',function(){
		second.style.cursor="pointer"
		alert("second 선택")
	})
}
</script>
</head>
<body>
	<div>
		<p>첫번째</p>
		<span>
			<p>두번째</p>
		</span>
	</div>
</body>
</html>