<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	글자조작 
	<h1>(변경)</h1> => 장바구니 => 결제
	
	textContext : 글자만 변경 => $().text()
	innerHTML : HTML을 포함해서 변경 => $().html()
	
	h1.style.backgroundColor (o)
	h1.style.background-color (x)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=>{
	let a = document.querySelector("#a")
	let b = document.querySelector("#b")
	
	a.textContent = "문자조작"
	b.innerHTML="<font color='red'>문자조작</font>"
	b.style.border="3px solid green"
	
	let btn = document.querySelectorAll("button")
	btn[0].textContent = "취소"
	btn[1].style.backgroundColor="yellow"
	
	document.body.style.backgroundColor="pink"
}
</script>
</head>
<body>
	<button type="button">삭제</button>
	<button type="button">HTML추가</button>
	<h1 id="a">Hello 문자조작(textContent)</h1>
	<h1 id="b">Hello HTML추가(innerHTML)</h1>
	<%--
		자바스크립트 : id중복 => 오류
		CSS : id가 중복되어도 구동(적용)
		------------------------------> id는 반드시 중복X
	 --%>
</body>
</html>