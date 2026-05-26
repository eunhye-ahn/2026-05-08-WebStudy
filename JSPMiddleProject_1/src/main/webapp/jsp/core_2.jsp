<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>구구단</h1>
<table>
<tr>
<% for(int k=2; k<=9;k++){ %>
<th>
	
	<%=k%>단
	</th>
</tr>
<%} %>
<% for(int i=2; i<=9;i++){ %>
<tr>
	<td>
	<% for(int j=1;j<=9;j++){ %>
		<%=i+"*"+j+"="+i*j %><br>
	<%} %>
	</td>
</tr>
	<%} %>
</table>
<h1>JSTL 구구단</h1>
<c:forEach var="i" begin="2" end="9" step="1">
${i}단<br>
<c:forEach var="j" begin="1" end="9" step="1">
${i}*${j}=${i*j}<br>
</c:forEach>
</c:forEach>

</body>
</html>