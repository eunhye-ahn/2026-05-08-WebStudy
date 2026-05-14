<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*,com.sist.dao.*,java.util.*"%>
<%
	FoodDAO dao = new FoodDAO();
	String type = request.getParameter("type");
	if(type == null) {
		type="한식";
	}
	String strPage=request.getParameter("page");
	if(strPage==null){
		strPage="1";
	}
	int curpage = Integer.parseInt(strPage);
	List<FoodVO> list = dao.foodListData(curpage, type);
	int totalpage = dao.foodTotalPage(type);
	System.out.println("요청:"+type);
	System.out.println("page:"+curpage);
	System.out.println("totalpage:"+totalpage);
	System.out.println("data:"+list);
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
 </style>
</head>
<body>
	<div class="container">
		<div class="row text-center">
			<a href="list.jsp?type=한식" class="btn btn-lg btn-danger">한식</a>
			<a href="list.jsp?type=양식" class="btn btb-lg btn-success">양식</a>
			<a href="list.jsp?type=중식" class="btn btb-lg btn-warning">중식</a>
			<a href="list.jsp?type=일식" class="btn btb-lg btn-info">일식</a>
			<a href="list.jsp?type=분식" class="btn btb-lg btn-primary">분식</a>
		</div>
		<div>
			    <%
       for(FoodVO vo:list)
       {
    %>
      <div class="col-md-3"> <!-- 숫자합 12가 되면 자동으로 다음줄로 내려간다 -->
	    <div class="thumbnail">
	      <a href="#">
	        <img src="<%=vo.getPoster() %>" alt="" style="width:240px;height: 150px">
	        <div class="caption">
	          <p><%=vo.getName() %></p>
	        </div>
	      </a>
	    </div>
	  </div>
	<%
       }
	%>
		</div>
		<div class="row text-center">
			<a href="list.jsp?page=<%= curpage>1? curpage-1:curpage %>&type=<%=type %>" class="btn btn-sm btn-warning">이전</a>
				<%=curpage %>page / <%= totalpage %> pages
			<a href="list.jsp?page=<%= curpage<totalpage? curpage+1:curpage %>&type=<%=type %>" class="btn btn-sm btn-warning">다음</a>
		</div>
	</div>
</body>
</html>