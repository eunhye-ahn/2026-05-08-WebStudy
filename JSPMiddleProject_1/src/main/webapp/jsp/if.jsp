<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Java 조건문</h1>
<%
	for(int i=0; i<=10; i++){
		if(i%2==0){
			%>
			<%=i%><br>
			<%
		}
	}
%>
<h1>JSTL 조건문</h1>
<c:forEach var="i" begin="0" end="10" step="1">
<c:if test="${i%2==0}">
	${i }<br>
</c:if>
</c:forEach>
<h1>다중 조건문</h1>
<%
int star = 3;
%>
<%
if(star==0){
%>
<span style="color:orange">☆☆☆☆☆</span>
<%
}
else if(star==1){
%>
<span style="color:orange">★☆☆☆☆</span>
<%
}
else if(star==2){
%>
<span style="color:orange">★★☆☆☆</span>
<%
}
else if(star==3){
%>
<span style="color:orange">★★★☆☆</span>
<%
}
else if(star==4){
%>
<span style="color:orange">★★★★☆</span>
<%
}
else if(star==5){
%>
<span style="color:orange">★★★★★</span>
<%
}
%>
<h1>JSTL 다중조건문</h1>
<c:set var="star" value="3"/>
<c:choose>
<c:when  test="${star==0 }"><span>☆☆☆☆☆</span></c:when>
<c:when  test="${star==1 }"><span style="color:orange">★☆☆☆☆</span></c:when>
<c:when  test="${star==2 }"><span style="color:orange">★★☆☆☆</span></c:when>
<c:when  test="${star==3 }"><span style="color:orange">★★★☆☆</span></c:when>
<c:when  test="${star==4 }"><span  style="color:orange">★★★★☆</span></c:when>
<c:otherwise><span style="color:orange">★★★★★</span></c:otherwise>
</c:choose>
<h1>선택조건문</h1>
<c:set var="gender" value="1"/>
<c:if test="${gender == 1 }">
<span>남자</span>
</c:if>
<c:if test="${gender != 1 }">
<span>여자</span>
</c:if>
</body>
</html>