<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.row{
	margin: 0 auto;
	width:960px;
}
p{
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
			      <a href="../food/detail.do?no=${vo.no }">
			        <img src="${vo.poster}" alt="Lights" style="width:240px;heigth:120px;object-fit:cover">
			        <div class="caption">
			          <p>${vo.name }</p>
			        </div>
			      </a>
			    </div>
			  </div>
		</c:forEach>
		<div class="text-center" style="margin-top: 20px">
			<ul class="pagination">
				<c:if test="${startpage>1 }">
					<li><a href="../main/main.do?page=${startpage-1}">이전</a></li>
				</c:if>
					<c:forEach var="i" begin="${startpage }" end="${endpage }">
						<li ${i==curpage ? "class=active":"" }><a href="../main/main.do?page=${i }">${i }</a></li>
					</c:forEach>
				<c:if test="${endpage<totalpage }">
					<li><a href="../main/main.do?page=${endpage+1 }">다음</a></li>
				</c:if>
			</ul>
		</div>
		</div>
	</div>
</body>
</html>