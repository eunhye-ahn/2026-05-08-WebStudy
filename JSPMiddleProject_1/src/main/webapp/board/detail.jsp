<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<jsp:useBean id="model" class="com.sist.model.BoardModel"/>
<%
	model.boardDetail(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <style>
 .conatiner{
 	margin-top: 50px;
 }
 .row{
 	margin: 0 auto;
 	widht: 800px;
 }
 h3 {
 	text-align : center;
 }
 </style>
</head>
<body>
<div class="container">
	<div class="row">
	<h3>내용보기</h3>
		<table class="table">
			<tr>
				<th class="text-center" width=20%>번호</th>
				<td class="text-center" width=30%>${vo.no }</td>
				<th class="text-center" width=20%>작성일</th>
				<td class="text-center" width=30%>${vo.dbday }</td>
			</tr>
			<tr>
				<th width=20% class="text-center success">제목</th>
				<td colspan="3">${vo.subject }</td>
			</tr>
			<tr>
			<td class="text-left" colspan="4" valign="top" height="200">
				<pre style="white-space: pre-wrap;">${vo.content }</pre>
			</td>
			</tr>
		</table>
		<table>
			<tr>
			<td class="text-right">
				<a href="list.jsp" class="btn btn-sm btn-success">목록</a>
				<a href="list.jsp" class="btn btn-sm btn-danger">삭제</a>
			</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>