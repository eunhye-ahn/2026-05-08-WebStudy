<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mode = request.getParameter("mode");
if(mode == null) {
	mode="1";
}
int index = Integer.parseInt(mode);
String jsp = "";
switch(index){
case 1:
	jsp="home.jsp";
	break;
case 2:
	jsp="script.jsp";
	break;
case 3:
	jsp="object.jsp";
	break;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<% 
pageContext.include("header.jsp");
%>
<% 
pageContext.include(jsp);
%>
</body>
</html>