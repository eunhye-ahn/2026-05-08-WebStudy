<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb" info="연습파일"%>
<%--
buffer : 임시저장소 => html이 저장 위치

많이쓰는 page 속성
errorPage isErrorPage contentType import
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
현재 사용중인 메모리 크기:<%=out.getBufferSize()-out.getRemaining() %>
</body>
</html>