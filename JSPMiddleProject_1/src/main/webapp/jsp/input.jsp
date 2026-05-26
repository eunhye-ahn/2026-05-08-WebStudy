<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="output.jsp?pwd=1234">이동</a>
	<form method="post" action="output.jsp">
		<input type="checkbox" name="hobby" value="등산" checked>등산
		<input type="checkbox" name="hobby" value="여행">여행
		<input type="checkbox" name="hobby" value="낚시">낚시
		<button>제출</button>
	</form>
</body>
</html>