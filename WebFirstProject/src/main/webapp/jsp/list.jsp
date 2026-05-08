<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.vo.*,com.sist.dao.*"%>
<%--
   자바 + HTML 
 --%>
<%--
   오라클에 있는 데이터 읽기 
 --%>
<%
   String strPage=request.getParameter("page");
   if(strPage==null)
	   strPage="1";
   int curpage=Integer.parseInt(strPage);
   
   // 자바 코딩 위치 
   FoodDAO dao=new FoodDAO();
   List<FoodVO> list=dao.foodListData(curpage);
   int totalpage=dao.foodTotalPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
   margin-top: 50px; /* 출력 위치를 아래로 내린다 */
}
.row {
   margin: 0px auto; /*가운데 정렬*/
   width: 960px;
}
</style>
</head>
<body>
   <div class="container">
     <div class="row">
      <table class="table">
        <tr class="success">
          <th>번호</th>
          <th></th>
          <th>업체명</th>
          <th>음식종류</th>
          <th>주소</th>
        </tr>
        <%
           for(FoodVO vo:list)
           {
        %>
               <tr>
                 <td><%=vo.getNo() %></td>
                 <td><img src="<%=vo.getPoster() %>" width=30 height=30></td>
                 <td><%=vo.getName() %></td>
                 <td><%=vo.getType() %></td>
                 <td><%=vo.getAddress() %></td>
               </tr>
        <%
           }
        %>
      </table>
      <table class="table">
        <tr>
          <td class="text-center">
           <a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-danger">이전</a>
           <%= curpage %> page / <%= totalpage %> pages
           <a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-danger">다음</a>
          </td>
        </tr>
      </table>
     </div>
   </div>
</body>
</html>