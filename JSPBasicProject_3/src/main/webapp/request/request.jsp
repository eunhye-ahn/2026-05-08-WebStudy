<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"
    buffer="16kb" info="상품 목록" errorPage="error.jsp"
%>
<%
	String strPage = request.getParameter("page");
	if(strPage == null)
		strPage = "1";
	/*
	http://localhost/JSPBasicProject_3/request/request.jsp
	*/
	int curpage = Integer.parseInt(strPage);
	//데이터베이스 연동
	
	GoodsDAO dao = new GoodsDAO();
	List<GoodsVO> list = dao.goodsListData(curpage);
	
	int totalpage = dao.goodsTotalData();
	
	final int BLOCK = 10;
	int startpage = ((curpage-1)/BLOCK*BLOCK)+1;
	int endpage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
	
	if(endpage >totalpage){
		endpage = totalpage;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
	font-family: sans-serif;
	padding : 40px;
	background : lightyellow;
}
.card-wrap{
	display: flex;
	flex-wrap: wrap; /*이미지 배열 => 범위를 벗어나면 아래로 내려라*/
	gap: 20px;
	
}
.card{
	width: calc(25% - 15px); /*한줄에 4개씩 출력*/
	background: white;
	border-radius: 12px; 
	overflow: hidden;
	text-transform: 0.3s	/*수행시간 3초*/
}
.card:hover{
	transform: translateY(-5px); /*살짝위로올리기*/
	
}
.card img{
	width: 100%;
	height: 220px;
	object-fit: cover; /*이미지를 화면크기에 맞게 꽉채우기*/
	display:block;
}
.pagination{
	margin : 0px auto;
	margin-top: 10px;
	display: flex;
	gap: 10px;
	justify-content: center;
	align-items: center;
}
.pagination a{
	text-decoration: none;
	border: 1px solid gray;
	transition: 0.2s;
	background-color: lightgray;
	padding: 10px;
	border-radius: 30px;
}
.pagination a:active{
	background-color: blue;
}
.card-body{
	padding: 15px;
}
.card-title{
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 10px;
	/*...만들기*/
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
.card-text{
	color: #666;
	font-size: 14px;
}
.detail{
	text-decoration: none;
	color: black;
}
.detail:hover{
	color: orange;
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="card-wrap">
	<% for(GoodsVO vo : list){
		
	%>
		<div class="card">
			<img src="<%=vo.getGoods_poster()%>">
			<div class="card-body">
			<a href="detail.jsp?no=<%=vo.getNo() %>" class="detail">
				<div class="card-title"><%=vo.getGoods_name() %></div>
				</a>
				<div class="card-text"><%=vo.getGoods_price()%></div>
			</div>
		</div>
		<%
	}
		%>
	</div>
	<div class="pagination">
		<a href="request.jsp?page=<%=startpage -1 %>">&laquo;</a>
		<%
		for(int i = startpage; i<=endpage; i++){
		%>
		<a href="request.jsp?page=<%=i%>"><%=i %></a>
		<% } %>
		<a href="request.jsp?page=<%=endpage-1%>">&raquo;</a>
	</div>
</body>
</html>