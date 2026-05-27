<%@page import="com.sist.model.FoodModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
	FoodModel model = new FoodModel();
	model.foodListData(request);//보내준 request에 출력값 데이터를 담아서 달라
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
 	widht: 960px;
 }
 p {
 	overflow: hidden;
 	white-space: nowrap;
 	text-overflow: ellipsis;
 }
 img {
 	width: 240px;
 	heigth: 150px;
 	object-fit: cover;
 }
 </style>
</head>
<body>
<div class="container">
	<div class="row">
			<c:forEach var="vo" items="${list}">
				<div class="col-md-3">
			    <div class="thumbnail">
			      <%-- 이동 => 쿠키 전송 --%>
			      <a href="../food/detail_before.jsp?no=${vo.no}">
			        <img src="${vo.poster}" title="${vo.address}">
			        <div class="caption">
			          <p>${vo.name}</p>
			        </div>
			      </a>
			    </div>
			  </div>
			</c:forEach>
	</div>
		<div class="row text-center" style="margin-top: 20px">
		<ul class="pagination">
		<c:if test="${startpage>1}">
			<li><a href="list.jsp?page=${startpage-1}">&laquo;</a></li>
		</c:if>
			<c:forEach var="i" begin="${startpage }" end="${endpage }">
				<li ${i==curpage?"class=active":"" }><a href="list.jsp?page=${i}">${i}</a></li>
			</c:forEach>
		<c:if test="${endpage<totalpage}">
			<li><a href="list.jsp?page=${endpage+1}">&raquo;</a></li>
		</c:if>
		</ul>
	</div>
</div>
</body>
</html>