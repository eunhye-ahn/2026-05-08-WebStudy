<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = request.getParameter("name");
String password = request.getParameter("password");
String gender = request.getParameter("gender");
String content = request.getParameter("content");
String day = request.getParameter("day");
String[] hobby = request.getParameterValues("hobby");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름:<%=name %>
비밀번호:<%=password %>
성별:<%=gender %>
내용:<%=content %>
생일:<%=day %>
취미:
<ul>
	<%
	if(hobby != null){
		for(String h : hobby){
			%>
			<li><%=h %></li>
			<%	
		}
	}else{
		%>
		<li>취미가 없습니다</li>
	<%
	}
	%>  
</ul>
<ul>
	<li><%=request.getRequestURI() %></li>
	<li><%=request.getRequestURL() %></li>
</ul>
</body>
</html>