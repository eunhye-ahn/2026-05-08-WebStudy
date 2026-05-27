<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%
	List<String> list = new ArrayList<String>();
	list.add("길동");
	list.add("길동");
	list.add("길동");
	
	request.setAttribute("list", list);
	session.setAttribute("list", list);
%>
<%-- 
core
=>변수선언 <c:set var="name" value="홍길동"/>
=>제어문
반복문 : 
<c:forEach var="i" begin="1" end="10" step="1"></c:forEach> 
<c:forEach var="name" items="${list}"></c:forEach>
조건문 :<c:if test="${i%2==0}"
다중조건문 : 
<c:choose>
<c:when test="${i==1}"></c:when>
...
<c:otherwise></c:otherwise>
</c:choose>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>고전</h1>
	<ul>
	<% for(String name: list) { %>
		<li><%=name %></li>
	<%} %>
	</ul>
	<h1>request</h1>
	<ul>
	<c:forEach var="name" items="${list }">
		<li>${name }</li>
	</c:forEach>
	</ul>
	<h1>session</h1>
	<ul>
	<c:forEach var="name" items="${sessionScope.list }">
		<li>${name }</li>
	</c:forEach>
	</ul>
	<hr>
	<h1>조건 처리</h1>
	<%
		for(int i=1;i<=10;i++){
			if(i%2==0){
	%>
		<%=i%>&nbsp;
	<%
			}
		}
	%>
	<h1>JSTL</h1>
	<c:forEach var="i" begin="1" end="10" step="1">
		<c:if test="${i%2==0}">
			${i }&nbsp;
		</c:if>
	</c:forEach>
	<hr>
	<h1>다중조건문</h1>
	<%
		for(int i=1;i<=10;i++){
			if(i%2==0){
	%>
		<%=i+"는 짝수입니다"%><br>
	<%
			}else{
	%>
		<%=i+"는 홀수입니다" %><br>
	<%
			}
		}
	%>
	<h1>JSTL</h1>
	<c:forEach var="i" begin="1" end="10">
		<c:choose>
			<c:when test="${i%2==0}">
			${i+="는 짝수입니다" }<br>
			</c:when>
			<c:otherwise>
			${i+="는 홀수입니다" }<br>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<hr>
	<h1>변환:날짜</h1>
	<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);
	%>
	<%=date %>
	<%=today %><br>
	<c:set var="date" value="<%=date %>" />
	<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/><br>
	<h1>변환:숫자</h1>
	<%
	int won = 134564;
	DecimalFormat df = new DecimalFormat("#,###");
	String s = df.format(won);
	%>
	<%=s %><br>
	<c:set var="won" value="<%=won %>"/>
	<fmt:formatNumber value="${won }" pattern="#,###" /><br>
</body>
</html>