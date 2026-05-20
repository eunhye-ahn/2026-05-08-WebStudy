<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
Cookie

reqeust 객체 
1)클래스
HttpServletRequest
1.서버정보
getServerInfo() : 서버이름출력
getProtocol() : 사용된프로토콜
getMethod() : GET / POST
getRequestURl()
getRequestURI()
getContextPath()

http://localhost/JSPBasicProject_3/jsp/jstl.jsp =>URL
----------------서버정보
				-------------------------------client요청정보
1)getServerInfo() : localhost
2)getProtocol() : http => 80
3) getRequestURl(): /JSPBasicProject_3/jsp/jstl.jsp
4)getContextPath() : 루트폴더 /JSPBasicProject_3

브라우저정보 => 로그파일만들기
1) 사용자의 IP : getRemoteAddr()
2) 사용자의 PROT : getPort()
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반자바</h1>
<%
	for(int i=1; i<=10; i++){
		
%>
<%=i %>&nbsp;
<%
	}
%>
<h1>JSTL</h1>
<c:forEach var="i" begin="1" end="10" step="1">
<c:if test="${i%2==0}">
${i }&nbsp;
</c:if>
</c:forEach>
<br>
<h1>request (서버정보)</h1>
<ul>
	<li>서버이름: <%=request.getServerName() %></li>
	<li>프로토콜: <%=request.getProtocol() %></li>
	<li style="color:red">루트패스: <%=request.getContextPath() %></li>
	<li style="color:red">URI: <%=request.getRequestURI() %></li>
</ul>
<h1>request (브라우저정보)</h1>
<ul>
	<li>IP: <%=request.getRemoteAddr() %></li>
	<li>IP: <%=request.getRemotePort() %></li>
</ul>
<h1>사용자요청정보</h1>
<ul>
	<li>단일값 : getParameter()</li>
	<li>단일값 : getParameter()</li>
</ul>
</body>
</html>