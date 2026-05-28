<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel ="stylesheet" href="style.css">
</head>
<body>
<%--
입력된 모든 값을 전송
url뒤에 데이터 노출 get
단순한 검색/페이지번호.상세보기번호
a location.href sendRedirect

post 데이터 감춰서 전송
데이터가 많은 경우/ 보안이 필요한 전송

 --%>
<div class="container">
	<div class="row">
		<h3>글쓰기</h3>
		<form method="post" action="insert_ok.jsp">
		<table class="table">
			<tr>
				<th width=10% class="text-center">이름</th>
				<td width=90%><input type="text" size=20 name=name class="input-sm" required></td>
			</tr>
			<tr>
				<th width=10% class="text-center">제목</th>
				<td width=90%><input type="text" size=60 name=subject class="input-sm" required></td>
			</tr>
			<tr>
				<th width=10% class="text-center">내용</th>
				<td width=90%>
					<textarea rows="10" cols="61" name=content required></textarea>
				</td>
			</tr>
			<tr>
				<th width=10% class="text-center">비밀번호</th>
				<td width=90%><input type="password" size=60 name=pwd class="input-sm" required></td>	
			</tr>
			<tr >
				<td colspan="2" class="text-center">
					<button class="btn-sm btn-primary">글쓰기</button>
					<button class="btn-sm btn-primary" type="button"
					onclick="javascript:history.back()">취소</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>