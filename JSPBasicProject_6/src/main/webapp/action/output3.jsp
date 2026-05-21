<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="com.sist.bean.MemberBean">
<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>받은 데이터 1</h1>
	이름:<%=bean.getName() %><br>
	나이:<%=bean.getAge() %><br>
	성별:<%=bean.getGender() %><br>
	주소:<%=bean.getAddress() %><br>
	번호:<%=bean.getPhone() %><br>
	소개:<%=bean.getContent() %><br>
<hr>
	<h1>받은 데이터 2</h1>
	이름:<jsp:getProperty name="bean" property="name"/><br>
	나이:<jsp:getProperty name="bean" property="age"/><br>
	성별:<jsp:getProperty name="bean" property="gender"/><br>
	주소:<%=bean.getAddress() %><br>
	번호:<%=bean.getPhone() %><br>
	소개:<%=bean.getContent() %><br>
</body>
</html>