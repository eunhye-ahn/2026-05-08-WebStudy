<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSP
		지시자: page/taglib
				----
				|contentType
	${}
	1.연산자
	+ : 순수하게 덧셈만 가능 => 문자열결합 X
	/ : 정수/정수=실수 ${5/2} ${5 div 2}
	% : ${5%2} ${5 mod 2}
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL 연산자</h1>
	<h2>산술 연산자</h2>
	&#36;{10+10}=${10+10}<br>
	&#36;{10+null}=${10+null}<br>
	&#36;{"10"+10}=${"10"+10}<br>
	&#36;{"10"+=10}=${"10"+=10}<br>
	&#36;{10/3}=${10/3}<br>
	<h2>비교 연산자</h2>
	==(eq) !=(ne) >(gt) <(lt) >=(ge) <=(le)<br>
	비교연산자는 문자열/날짜/숫자 비교시에도 사용<br>
	<h2>empty 공백 연산자</h2>
	<%
	request.setAttribute("name","");
	String name = "심청이";
	
	session.setAttribute("name",name);
	%>
	&#36;{empty name}=${empty name}<br>
	<%-- ${}은 request/session에 있는 경우에만 값 출력 가능 (일반변수X) --%>
	${sessionScope.name}<br>
<!-- 	생략은 할 수 잇지만 request가 우선적용되어출력됨 -->
	&#36;{10==10?"T":"F"}=${10==10?"T":"F"}<br>

</body>
</html>