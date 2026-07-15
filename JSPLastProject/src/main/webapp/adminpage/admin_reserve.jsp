<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<main class="admin-main">
    <h2>📅 예약 목록</h2>

    <section class="recent-section">
    	<table class="recent-section">
    		<tr>
    			<th>번호</th>
    			<th></th>
    			<th>아이디</th>
    			<th>이름</th>
    			<th>맛집명</th>
    			<th>예약일</th>
    			<th>시간</th>
    			<th>인원</th>
    			<th></th>
    		</tr>
    		<c:forEach var="vo" items="${list }">
    			<tr>
	    			<td>${vo.rno }</td>
	    			<td><img src="${vo.fvo.poster }" style="width:35px;height: 35px"></td>
	    			<td>${vo.id}</td>
	    			<td>${vo.name }</td>
	    			<td>${vo.fvo.name }</td>
	    			<td>${vo.rdate }</td>
	    			<td>${vo.rtime }</td>
	    			<td>${vo.inwon }</td>
	    			<td>
	    				<c:if test="${vo.ok=='N' }">
		    				<a class="btn btn-xs btn-danger" href="../adminpage/reserve_ok.do?rno=${vo.rno }">대기</a>
		    			</c:if>
		    			<c:if test="${vo.ok=='Y' }">
		    				<span class="btn btn-xs btn-default" href="#">완료</span>
		    			</c:if>
	    			</td>
    			</tr>
    		</c:forEach>
    	</table>
    </section>
</main>
</body>
</html>