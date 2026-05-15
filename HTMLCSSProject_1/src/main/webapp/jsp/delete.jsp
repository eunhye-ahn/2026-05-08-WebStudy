<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
</style>
</head>
<body>
   <div class="container">
     <div class="board-wrap">
       <h3>삭제하기</h3>
       <div class="row">
         <table class="table">
          <tbody>
                    	<form method="post" action="delete_ok.jsp">
          	<tr>
          		<td>
          			비밀번호: <input type=password size=15 class="input-sm" name="pwd">
          			<input type="hidden" name="no" value="<%= no %>" >
          		</td>

          		<tr>
	          		<td>
	          			<input type="submit" value="삭제">
	          			<input type="button" value="취소"
	          			onclick="javascript:history.back()"
	    				>
	          		</td>
          		</tr>
          		          		</form>
          	</tr>
          </tbody>
         </table>
       </div>
     </div>
   </div>
</body>

</html>