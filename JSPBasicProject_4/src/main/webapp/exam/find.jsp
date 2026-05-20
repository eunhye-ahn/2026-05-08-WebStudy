<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.sist.vo.FoodVO"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
	//사용자가 전송한 주소,페이지 받기
	String address = request.getParameter("address");
	String strpage = request.getParameter("page");
	if(address == null){
		address = "마포";
	}
	if(strpage == null){
		strpage = "1";
	}
	int curpage = Integer.parseInt(strpage);
	FoodDAO dao = new FoodDAO();
	List<FoodVO> list = dao.foodFindData(curpage, address);
	int totalpage = dao.foodFindTotalPage(address);
	
	Map map = new HashMap();
	map.put("curpage",curpage);
	map.put("totalpage", totalpage);
	map.put("list",list);
	
	ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(map);
%>
<%=json%>