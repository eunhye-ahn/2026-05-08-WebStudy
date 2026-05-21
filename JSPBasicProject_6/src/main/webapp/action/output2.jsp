<%@page import="com.sist.bean.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");
	String content = request.getParameter("content");
	
	MemberBean bean = new MemberBean();
	bean.setName(name);
	bean.setGender(gender);
	bean.setAddress(address);
	bean.setContent(content);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>받은 데이터</h1>
	이름:<%=bean.getName() %><br>
	성별:<%=bean.getGender() %><br>
	주소:<%=bean.getAddress() %><br>
	번호:<%=bean.getPhone() %><br>
	소개:<%=bean.getContent() %><br>
</body>
</html>