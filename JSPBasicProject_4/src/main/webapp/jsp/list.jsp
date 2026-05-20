<%@page import="com.sist.vo.FoodVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.dao.FoodDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String param = request.getParameter("page");
    if(param == null){
    	param = "1";
    }
        int curpage = Integer.parseInt(param);

    	FoodDAO dao = new FoodDAO();
    	List<FoodVO> list = dao.foodListData(curpage);
    	
    	int totalpage = dao.foodTotalPage();
  		
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
 .container {
   margin-top: 50px;
 }
 .row {
   /* 화면에 출력 */
   margin: 0px auto;
   width:960px;
 }
 p {
     overflow: hidden;
     white-space: nowrap;
     text-overflow: ellipsis;
 }
 pagination {
 	display: inline-block;
 }
</style>
</head>
<body>
<div class="container">
<div class="row">
<% for(FoodVO vo: list) { %>
<div class="col-md-4">
    <div class="thumbnail">
      <a href="/w3images/lights.jpg">
        <img src="<%=vo.getPoster() %>" alt="Lights" style="width:100%">
        <div class="caption">
        <a href="detail_before.jsp?no=<%=vo.getNo()%>">
          <p><%= vo.getName() %></p>
          </a>
        </div>
      </a>
    </div>
  </div>
  <%
  }
  %>
    </div>
      </div>
      <div class="pagination">
      	<a href="list.jsp?page=<%=curpage-1%>">이전</a>
      	<a href="list.jsp?page=<%=curpage+1%>">다음</a>
      </div>
</body>
</html>