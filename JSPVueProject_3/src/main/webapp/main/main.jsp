<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container text-right">
		ID:<input type="text" size="15" ref="idRef" class="input-sm">
		&nbsp;
		PWD:<input type="password" size="15" ref="pwdRef" class="input-sm">
		&nbsp;
		<button class="btn-sm btn-danger">로그인</button>
</div>
<hr>
<jsp:include page="${main_jsp }"></jsp:include>
</body>
</html>