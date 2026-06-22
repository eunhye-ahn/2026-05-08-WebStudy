<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
	HTML : 태그 = 사용자정의 불가능
			속성 = 사용자정의 가능
			================== 값을 숨긴 상태에서 값 읽기 (jquery에서 가능) ??
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 700px;
}
h3 {
   text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#year').on('change',function(){
		$('#frm').submit()
	})
	$('#month').on('change',function(){
		$('#frm').submit()
	})
})
</script>
</head>
<body>
<div class="container">
	<div class="row">
		<h3>${year }년도 ${month }월 ${today }일</h3>
	</div>
	<div class="row" style="margin-top:10px">
		<form method="post" action="../reserve/diary.do" id="frm">
		<table class="table">
			<tr>
				<td>
					<select name="year" class="input-sm" id="year">
						<c:forEach var="i" begin="2026" end="2030">
							<option ${i==year ? "selected":"" }>${i }</option>
						</c:forEach>
					</select>년도 &nbsp;
					<select name="month" class="input-sm" id="month">
						<c:forEach var="i" begin="1" end="12">
							<option ${i==month ? "selected":"" }>${i }</option>
						</c:forEach>
					</select>월 &nbsp;
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div class="row" style="margin-top:20px">
		<table class="table">
			<tbody>
				<tr class="danger">
				<%--요일출력 --%>
				<c:set var="k" value="0" />
				<c:set var="color" value="black" />
					<c:forEach var="w" items="${strWeek }">
					<c:choose>
						<c:when test="${k==0 }">
						<c:set var="color" value="red" />
						</c:when>
						<c:when test="${k==6 }">
						<c:set var="color" value="blue" />
						</c:when>
						<c:otherwise>
						<c:set var="color" value="black" />
						</c:otherwise>
					</c:choose>
						<th style="height:30px;color:${color}" class="text-center">${w }</th>
						<c:set var="k" value="${k+1 }" />
					</c:forEach>
				</tr>
				<%-- week 변수설정 --%>
				<c:set var="week" value="${week }"/>
				<%--1일부터 날짜출력 end:포함 --%>
				<c:forEach var="i" begin="1" end="${lastday }">
				<%--맨처음에 요일까지 공백 띄기(1번만 실행) --%>
					<c:if test="${i==1 }">
						<tr>
							<c:forEach var="j" begin="1" end="${week }">
								<td class="text-center">&nbsp;</td>
							</c:forEach>
					</c:if>
					<%--일자출력 --%>
					<td class="text-center ${today==i?'success':'' }">${i }</td>
					<%-- 요일 한개씩 증가 : 토요일까지 --%>
					<c:set var="week" value="${week+1 }"/>
					<c:if test="${week>6 }">
						<%-- 토요일이면 일요일부터 시작 --%>
						<c:set var="week" value="0"/>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>