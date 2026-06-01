<%@page import="com.sist.vo.DataBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 900px;
}
h3 {
	text-align: center;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<table class="table">
			<tr>
				<td>
					<a href="insert.do" class= "btn btn-sm btn-warning">새글</a>
				</td>
			</tr>
		</table>
		<h3>자료실</h3>
		<table class="table">
			<tr class="success">
				<th class="text-center" width=10%>번호</th>
				<th class="text-center" width=45%>제목</th>
				<th class="text-center" width=15%>이름</th>
				<th class="text-center" width=20%>작성일</th>
				<th class="text-center" width=10%>조회수</th>
			</tr>
			
			<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center" width=10%>${vo.no }</td>
					<td width=45%><a href="detail.do?no=${vo.no }">${vo.subject }</a></td>
					<td class="text-center" width=15%>${vo.name }</td>
					<td class="text-center" width=20%>${vo.dbday }</td>
					<td class="text-center" width=10%>${vo.hit }</td>
				</tr>
			</c:forEach>
		</table>
		<table class="table">
			<tr>
				<td class="text-center">
					<a href="list.do?page=${curpage>1? curpage-1:curpage}" class="btn btn-sm btn-danger">이전</a>
					${curpage} page / ${totalpage} pages
					<a href="list.do?page=${curpage<totalpage? curpage+1:curpage}" class="btn btn-sm btn-danger">다음</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>