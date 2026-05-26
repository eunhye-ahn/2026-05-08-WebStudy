<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
List<String> list = new ArrayList<String>();
list.add("남자");
list.add("여자");
list.add("남자");
list.add("여자");
list.add("남자");

request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%for(int i=0;i<list.size();i++){%>
<%=i+1%>.<%=list.get(i) %><br>
<%} %>
<br>
<c:forEach var="item" items="${list }" varStatus="s">
${s.index+1}.${item }<br>
</c:forEach>
<hr>
<h1>Java StringTokenizer</h1>
<ul>
<%
String colors = "red,yellow,green";
StringTokenizer st = new StringTokenizer(colors, ",");
while(st.hasMoreTokens()){
%>
	<li><%=st.nextToken() %></li>
<%
}
%>
</ul>
<h1>JSTL &lt;c:forTokens var="" value="" delimt="" &gt;</h1>
<ul>
<c:forTokens var="color" items="red,yellow,green" delims=",">
<li>${color}</li>
</c:forTokens>
</ul>
</body>
</html>