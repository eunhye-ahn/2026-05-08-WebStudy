<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	position : 위치속성
		=> 원하는 위치에 출력이 가능하게 넘드는 속성
		=> static/relative/absolute/fixed/sticky
	static : 
		html 태그들은 기본  => top right left bottom => 무시
	relative : 
		원래자리에서 위치로 약간 이동
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.static{
	position: static;
	width:100px;
	height:100px;
	background-color: red;
	top: 20px;
	left: 20px; 
}
.relative {
	position: relative;
	top: 20px;
	left: 20px;
	width:100px;
	height:100px;
	background-color: yellow;
}
</style>
</head>
<body>
	<div class="static"></div>
	<div class="relative"></div>
</body>
</html>