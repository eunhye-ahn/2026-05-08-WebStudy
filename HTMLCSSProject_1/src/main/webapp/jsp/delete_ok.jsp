<%@page import="com.sist.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no =request.getParameter("no");
String pwd =request.getParameter("pwd");
BoardDAO dao = new BoardDAO();

boolean bcheck=dao.boardDeleteData(pwd, Integer.parseInt(no));
if(bcheck ==true){
	response.sendRedirect("list.jsp");
}
else{
%>
	<script>
		alert("비밀번호가 틀렸습니다");
		history.back();
	</script>
	<%
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>