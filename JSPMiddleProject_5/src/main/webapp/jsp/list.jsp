<%@page import="com.sist.model.BoardModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
BoardModel model = new BoardModel();
model.boardListData(request);
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
<div class="container">
	<div class="row">
		<h3>묻고 답하기</h3>
		<table class="table">
			<tr>
				<td><a href="insert.jsp" class="btn btn-sm btn-danger">새글</a></td>
			</tr>
		</table>
		<table class="table">
			<tr class="success">
				<th width="10%" class="text-center">번호</th>
				<th width="45%" class="text-center">제목</th>
				<th width="15%" class="text-center">이름</th>
				<th width="20%" class="text-center">작성일</th>
				<th width="10%" class="text-center">조회수</th>
			</tr>
			<c:set var="count" value="${count }"/>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td width="10%" class="text-center">${count }</td>
					<td width="45%">
					<c:if test="${vo.group_tab>0}">
					<c:forEach begin="1" end="${vo.group_tab }">
					&nbsp;&nbsp;
					</c:forEach>
					<img src="../image/re_icon.png">
					</c:if>
					<a href="detail.jsp?no=${vo.no }">
					${vo.subject}
					</a>
					<c:if test="${vo.dbday == today }">
					&nbsp;<sup><img src="../image/new.gif"></sup>
					</c:if>
					</td>
					<td width="15%" class="text-center">${vo.name }</td>
					<td width="20%" class="text-center">
					${vo.dbday }
					</td>
					<td width="10%" class="text-center">${vo.hit }</td>
				</tr>
				<c:set var="count" value="${count-1 }"/>
			</c:forEach>
		</table>
		<table class="table">
			<tr>
				<td class="text-left">
					<select class="input-sm">
						<option>이름</option>
						<option>제목</option>
						<option>내용</option>
					</select>
					<input type="text" size=15 class="input-sm">
					<button class="btn-sm btn-primary">검색</button>
				</td>
				<td class="text-right">
					<a href="list.jsp?page=${curpage>1?curpage-1:curpage}" class="btn btn-sm btn-info">이전</a>
					${curpage } page / ${totalpage } pages
					<a href="list.jsp?page=${curpage<totalpage?curpage+1:curpage}" class="btn btn-sm btn-warning">다음</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>