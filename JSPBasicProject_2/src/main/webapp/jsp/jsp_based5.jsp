<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String[] images = {
		"../images/m1.jpg",
		"../images/m2.jpg",
		"../images/m3.jpg",
		"../images/m4.jpg",
		};

String[] keys = {
		"a",
		"b",
		"c",
		"d",
};
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img{
	position : absolute;
}
</style>
</head>
<body>
	<%
		for(int i=0;i<images.length;i++){
	%>
		<img src="<%=images[i] %>" class="<%=keys[i]%>">
		<%
		}
		%>
</body>
</html>