<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
우선순위
request>session>application 
-->
<%
request.setAttribute("name", "홍길동");
session.setAttribute("name", "길복자");
application.setAttribute("name", "감순자");

%>
${sessionScope.name}
</body>
</html>