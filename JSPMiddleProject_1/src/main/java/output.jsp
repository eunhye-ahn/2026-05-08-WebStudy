<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String pwd=request.getParameter("pwd");
%>
<%=pwd %><br>
${param.pwd}
<!-- EL은 화면 출력용(request, session에 데이터를 첨부해서 보내기) -->
<%
String[] hobby = request.getParameterValues("hobby");

for(String h : hobby){
%>
	<%=h%>

<% 
	}
%>
<br>
${paramValues.hobby[0]}
${paramValues.hobby[1]}
${paramValues.hobby[2]}
</body>
</html>