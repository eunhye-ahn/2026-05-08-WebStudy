<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JAVA 날짜 변환</h1>
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);
%>
<%=today %>
<h1>JSTL 날짜 변환</h1>
<c:set var="today" value="<%= new Date() %>"/>
<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/><br>
<h1>JAVA 숫자 변환</h1>
<%
	int won = 1234567;
	DecimalFormat df = new DecimalFormat("#,###,###");
	String data = df.format(won);
%>
<%=data %>
<br>
<h1>JSTL 숫자 변환</h1>
<fmt:formatNumber value="123456" type="currency" />
<br>
<fmt:setLocale value="en_US"/>
<fmt:formatNumber value="123456" type="currency" /><br>
<fmt:formatNumber value="0.75" type="percent" />
</body>
</html>