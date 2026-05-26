<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
JSTL => java standard tag lib
core
1) 변수설정 : <c:set var="vo" value="<%=new SawonVO()%>">
=> request.setAttribute("vo", new SawonVO())
2) 화면 출력 : <c:out value="">
3) 제어문
<c:forEach> : 반복문
<c:if> : 조건문
<c:choose> : 다중조건문
<c:forTokens> : StringTokenizer
4) URL => 화면이동 : <c:redirect url=""> sendRedirect() 
--%>
<%
	String name = "홍길동";
	request.setAttribute("name", name);
	
	List<String> names= new ArrayList<String>();
	names.add("홍길동");
	names.add("홍길동1");
	names.add("홍길동2");
	names.add("홍길동3");
	
	 request.setAttribute("names", names);
%>
<!-- 
JSTL은 XML로 되어있다

XML기분문법
1.여는태그,닫는태그가 반드시 일치, 단독 태그 이용
대소문자 구분
값 입력 시 반드시 "" 
-->
<c:set var="name1" value="심청이"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${name}<br>
${name1}<br>
<%
int i = 1;
for(String n : names){
%>
<%=i %>.<%= n %><br>
<%
i++;
}
%>
<br>
<h1>JSTL이용한 for-each </h1>
<ul>
	<c:forEach var="n" items="${names }" varStatus="s">
		<li>${s.index+1 }.${n }</li>
	</c:forEach>
</ul>
</body>
</html>