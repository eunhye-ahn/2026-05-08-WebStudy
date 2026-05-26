<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String msg="홍길동";
%>
<h1>Java 문자열 처리</h1>
<%= msg.length() %><br>
<%=msg.substring(0,3) %><br>
<%=msg.replace("홍","심") %>
<h1>JSTL 문자열 처리</h1>
<c:set var="msg" value="홍길동"/>
${fn:length(msg)}<br>
${fn:substring(msg,0,3) }<br>
${fn:replace(msg,"홍","심") }
</body>
</html>