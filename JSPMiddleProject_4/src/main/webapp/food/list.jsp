<%@page import="com.sist.dao.FoodVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.dao.FoodDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//요청값받기
	String strpage = request.getParameter("page");
	if(strpage == null){
		strpage = "1";
	}
	int curpage = Integer.parseInt(strpage);

//db연결
	FoodDAO dao = FoodDAO.newInstance();
	List<FoodVO> list = dao.foodListData(curpage);
	int totalpage = dao.foodTotalpage();
	
	//블록별
	final int BLOCK = 10;
	int startpage = ((curpage-1)/BLOCK*BLOCK)+1; // 1,11,21 ...
	/**
	startpage 1 => curpage(1~10)
	startpage 2 => curpage(2~20)
	
	*/
	int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	if(endpage>totalpage){
		endpage=totalpage;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <style>
 .conatiner{
 	margin-top: 50px;
 }
 .row{
 	margin: 0 auto;
 	widht: 960px;
 }
 p {
 	overflow: hidden;
 	white-space: nowrap;
 	text-overflow: ellipsis;
 }
 img {
 	width: 240px;
 	heigth: 150px;
 	object-fit: cover;
 }
 </style>
</head>
<body>
<div class="container">
	<div class="row">
			<%
			for(FoodVO vo : list){
			%>
				<div class="col-md-3">
			    <div class="thumbnail">
			      <%-- 이동 => 쿠키 전송 --%>
			      <a href="../food/detail_before.jsp?no=<%=vo.getNo()%>">
			        <img src="<%=vo.getPoster() %>" title="<%=vo.getAddress()%>">
			        <div class="caption">
			          <p><%=vo.getName()%></p>
			        </div>
			      </a>
			    </div>
			  </div>
			<%
			}
			%>
	</div>
	<div class="row text-center" style="margin-top: 20px">
		<ul class="pagination">
		<%if(startpage>1){ %>
			<li><a href="list.jsp?page=<%=startpage-1%>">&laquo;</a></li>
		<%} %>
			<%
			for(int i=startpage;i<=endpage;i++) {
			%>
			<li <%=i==curpage?"class=active":"" %>><a href="list.jsp?page=<%=i%>"><%=i %></a></li>
			<%} %>
		<%if(startpage<totalpage){ %>
			<li><a href="list.jsp?page=<%=endpage+1%>">&raquo;</a></li>
		<%} %>
		</ul>
	</div>
</div>
</body>
</html>