<%@page import="com.sist.model.BoardModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardModel model = new BoardModel();
	model.boardInsert(request, response);
%>