<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*,com.sist.vo.*"%>

<% 
	String no = request.getParameter("no");
	BoardDAO dao = new BoardDAO();
	BoardVO vo = dao.boardDetailData(Integer.parseInt(no));
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

.table {
  background: white;
  border-radius: 12px;
  overflow: hidden;
}
pre{
	white-space: pre-wrap;
	background: white;
	border: none;
}

</style>

</head>
<body>
   <div class="container">
     <div class="board-wrap">
       <h3>내용보기</h3>
       <div class="row">
         <table class="table">
          <tbody>
            <tr>
             <th width="20%" class="text-center">이름</th>
             <td width="80%">
              <%=vo.getName() %>
             </td>
            </tr>
            <tr>
             <th width="20%" class="text-center">제목</th>
             <td width="80%">
              <%=vo.getSubject() %>
             </td>
            </tr>
            <tr>
             <th width="20%" class="text-center">내용</th>
             <td width="80%">
              <pre><%=vo.getContent() %></pre>
             </td>
            </tr>
            <tr>
             <th width="20%" class="text-center">조회수</th>
             <td width="80%">
              <%=vo.getHit() %>
             </td>
            </tr>
            
            <tr>
              <td colspan="2" class="text-center">
               <input type=submit class="btn-sm btn-info" value="수정">
               <a href="delete.jsp?no=<%=vo.getNo() %>">삭제</a>
               <a href="list.jsp">목록</a>
              </td>
            </tr>
          </tbody>
         </table>
       </div>
     </div>
   </div>
</body>
</html>