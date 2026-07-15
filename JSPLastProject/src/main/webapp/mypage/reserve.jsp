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
<main class="mypage-main">
    <h2>예약 정보</h2>
    <table class="table">
    	<tr>
    		<th width="10%">번호</th>
    		<th width="10%"></th>
    		<th width="20%">맛집명</th>
    		<th width="20%">예약일</th>
    		<th width="10%">시간</th>
    		<th width="10%">인원</th>
    		<th width="20%"></th>
    	</tr>
    	<c:forEach var="vo" items="${list }">
	    	<tr>
	    		<td width="10%">${vo.rno }</td>
	    		<td width="10%"><img src="${vo.fvo.poster }" style="width:35px;height:35px"></td>
	    		<td width="20%">${vo.fvo.name }</td>
	    		<td width="20%">${vo.rdate }</td>
	    		<td width="10%">${vo.rtime }</td>
	    		<td width="10%">${vo.inwon }</td>
	    		<td width="20%">
	    			<c:if test="${vo.ok=='N' }">
	    				<span class="btn btn-xs btn-default">대기</span>
	    				<a href="" class="btn btn-xs btn-warning">취소</a>
	    			</c:if>
	    			<c:if test="${vo.ok=='Y' }">
	    				<span class="btn btn-xs btn-danger">완료</span>
	    			</c:if>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
</main>
</body>
</html>