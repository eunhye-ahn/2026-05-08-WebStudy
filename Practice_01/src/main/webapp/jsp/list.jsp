<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*,com.sist.vo.*"%>
<%

    String strPage=request.getParameter("page");

    if(strPage==null)
    	strPage="1";
    int curpage=Integer.parseInt(strPage);
    EmpDAO dao=new EmpDAO();
    List<EmpVO> list=dao.empListData(curpage);
 
    int totalpage =dao.empTotalPage();
    
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
  margin: 0px auto; /* 가운데 정렬 */
  width: 800px;
}
h3 {
  text-align: center;
}
.board-wrap{
  background: #fff;
  padding: 30px;
  border-radius: 18px;
  box-shadow: 0 8px 25px rgba(0,0,0,0.08);
}
.btn-success{
  border: none;
  border-radius: 30px;
  padding: 8px 18px;
  font-weight: bold;
  transition:all 0.3s ease;
}
.btn-success{
  transform:tranlateY(-2px);
  box-shadow: 0 5px 15px rgba(0,206,201,0.4);
}
.table {
  background: white;
  border-radius: 12px;
  overflow: hidden;
}
.table tbody tr.a:hover{
  background: #f1f5ff;
  transform:scale(1.01);
}
</style>

</head>
<body>
   <div class="container">
    <div class="board-wrap">
     <h3>자유 게시판 (JDBC=CRUD)</h3>
     <div class="row">
	     <table class="table">
	       <tbody>
	         <tr>
	           <td>
	            <a href="insert.jsp" class="btn btn-sm btn-success">새글</a>
	            <%--
	                 btn => link , 입체효과 
	                 btn-xs / btn-md / btn-sm / btn-lg 
	                 btn-danger(red) / btn-success(green) / 
	                 btn-warning(주황색) 
	                 btn-info(cyan) / btn-primary(blue) / 
	                 btn-default(gray) 
	             --%>
	           </td>
	         </tr>
	       </tbody>
	     </table>
     </div>
     <div class="row" style="margin-top: 10px">
     <table class="table">
       <thead>
         <tr class="danger">
           <th width=10% class="text-center">번호</th>
           <th width=45% class="text-center">이름</th>
           <th width=15% class="text-center">직책</th>
         </tr>
       </thead>
       <tbody>
	         <%
	            for(EmpVO vo:list)
	            {
	         %>
	                 <tr class="a">
			           <td width=10% class="text-center"><%=vo.getNo() %></td>
			           <td width=15% class="text-center"><%= vo.getEname()%></td>
			           <td width=20% class="text-center"><%=vo.getJob() %></td>
			         </tr>  
	         <%
	            }
	         %>
       </tbody>
     </table>  
     </div>
     <div class="row text-center" style="margin-top: 20px">
       <a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-warning">이전</a>
       <%=curpage %> page / <%=totalpage %> pages
       <a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-warning">다음</a>
     </div>
    </div>
   </div>
</body>
</html>