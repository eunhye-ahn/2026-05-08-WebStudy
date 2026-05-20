<%@page import="com.sist.dao.GoodsDAO"%>
<%@page import="com.sist.dao.GoodsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String no = request.getParameter("no");
    GoodsDAO dao = new GoodsDAO(); 
    GoodsVO vo = dao.goodsDetailData(Integer.parseInt(no));
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=vo.getGoods_name() %>
</body>
</html>