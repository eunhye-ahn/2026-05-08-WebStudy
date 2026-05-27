<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
    <%--
    	tomcat 10이상 => jakarta.tags.core => jakarta
    	tomcat 10미만 => http://java.sun.com/jsp/jstl/core => javax
     --%>
    <%
    MusicModel model = new MusicModel();
    model.musicListData(request);
    //=>controller
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
 	widht: 1024px;
 }
 h3 {
 	text-align : center;
 }
 </style>
</head>
<body>
<div class="container">
	<div class="row">
		<h3>뮤직 top 200</h3>
		<table class="table">
			<tr class="success">
				<th width=10% class="text-center">순위</th>
				<th width=10% class="text-center"></th>
				<th width=10% class="text-center"></th>
				<th width=10% class="text-center">곡명</th>
				<th width=35% class="text-center">가수명</th>
			</tr>
			<!-- 데이터출력 -->
			<c:forEach var="vo" items="${list}">
			<tr>
				<td  width=10% class="text-center">${vo.no}</td>
				<td  width=10% class="text-center">
					<c:set var="id" value="" />
					<c:choose>
						<c:when test="${vo.state=='상승'}">
							<font color="red">${"▲ "+= vo.idcrement }</font>
						</c:when>
						<c:when test="${vo.state=='하강'}">
							<font color="blue">${"▼ "+= vo.idcrement }</font>
						</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
				</td>
				<td  width=10% class="text-center">
					<img src="${vo.poster }" width=30 height=30>
				</td>
				<td  width=35% class="text-center">${vo.title}</td>
				<td  width=35% class="text-center">
				${vo.singer}
				<sub>${vo.album }</sub>
				</td>
			</tr>
			</c:forEach>
		</table>
		<table class="table">
			<tr>
				<td class="text-center">
					<a href="list.jsp?page=${curpage>1 ? curpage-1:curpage}" class="btn btn-sm btn-primary">이전</a>
					${curpage }page /${totalpage } pages
					<a href="list.jsp?page=${curpage<totalpage ? curpage+1:curpage}" class="btn btn-sm btn-primary">다음</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>