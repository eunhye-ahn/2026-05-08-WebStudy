<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
p {
	overflow: hidden;
	white-space: nowrap;	
	text-overflow: ellipsis;	
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
				    <div class="thumbnail">
				      <a href="#">
				        <img src="${vo.poster }" alt="${vo.address }" style="width:240px;120px;object-fit:cover">
				        <div class="caption">
				          <p><a href="../food/detail.do?no=${vo.no }">${vo.name }</a></p>
				        </div>
				      </a>
				   </div>
				 </div>
			</c:forEach>
		</div>
	</div>
</body>
</html>