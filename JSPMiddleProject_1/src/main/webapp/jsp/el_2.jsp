<%@page import="com.sist.vo.SawonVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	SawonVO vo = new SawonVO();
    	vo.setSabun(1);
    	vo.setName("홍길동");
    	
    	request.setAttribute("vo", vo);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이전</h1>
	이름:<%=vo.getName() %>
	<h1>현재</h1>
	이름:${vo.getName()}
	이름:${vo.name}
</body>
</html>