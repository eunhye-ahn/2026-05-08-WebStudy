<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String title="글자속성";
	String h2="글자색상";
	String[] colors= {"빨강", "파랑", "초록"};
%>
<h1><%= title %></h1>
<h2><%= h2 %></h2>
<p class="exam" style="color:red"><%=colors[0] %></p>
<p class="exam" style="color:blue"><%=colors[1] %></p>
<p class="exam" style="color:green"><%=colors[2] %></p>
<table>
<tr>
<% for (int i=2;i<=9;i++){ %>
	<td><%= i %></td>
<% } %>
</tr>
</table>
</body>
</html>