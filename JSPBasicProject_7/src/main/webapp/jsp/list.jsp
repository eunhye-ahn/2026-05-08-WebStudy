<%@page import="com.sist.dao.EmpBean"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.dao.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EmpDAO dao = new EmpDAO();
	List<EmpBean> list = dao.empListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<body>
	<h1>사원목록</h1>
	<table border = 1 bordercolor=black width=500>
		<tbody>
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>직위</th>
				<th>입사일</th>
				<th>급여</th>
			</tr>
			<% for(EmpBean bean : list)
				{
			%>
			<tr>
				<td><%=bean.getEmpno() %></td>
				<td><%=bean.getEname() %></td>
				<td><%=bean.getJob() %></td>
				<td><%=bean.getDbday() %></td>
				<td><%=bean.getSal() %></td>
				</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>