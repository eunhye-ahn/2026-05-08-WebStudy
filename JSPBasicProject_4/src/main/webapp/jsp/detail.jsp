
<%@page import="com.sist.vo.FoodVO"%>
<%@page import="com.sist.dao.FoodDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	FoodDAO dao = new FoodDAO();
	FoodVO vo = dao.foodDetailPage(Integer.parseInt(no));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.container{
	margin-top: 50px;
}
.row{
	width:800px;
	margin: 0px auto;
}
tr{
	border-bottom: 1px solid gray;
}
tr:last-child{
	border: none;
}
span{
	color: orange;
}
pre{
	white-space: pre-wrap;
	word-break: break-all;
	width:100%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table-content">
				<tbody>
					<tr>
						<td width=30% class="text-center" rowspan="5">
							<img src="<%=vo.getPoster() %>" style="width:70%; height:70%"
							class="img-rounded">
						</td>
						<td colspan="2">
							<h3><%=vo.getName() %>&nbsp;<span><%=vo.getScore() %></span></h3>
						</td>
					</tr>
					<tr>
						<td width=10% class="text-center">음식종류</td>
						<td><%=vo.getType() %></td>
					</tr>
					<tr>
						<td width=10% class="text-center">주소</td>
						<td><%=vo.getAddress() %></td>
					</tr>
					<tr>
						<td width=10% class="text-center">번호</td>
						<td><%=vo.getPhone() %></td>
					</tr>
					<tr>
						<td width=10% class="text-center">주차</td>
						<td><%=vo.getParking() %></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
							<button class="btn-xs btn-danger">좋아요</button>
							<button class="btn-xs btn-success">찜하기</button>
							<button class="btn-xs btn-warning">예약</button>
							<button class="btn-xs btn-primary"
							onclick="javascript:history.back()"
							>목록</button>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<pre><%= vo.getTheme() %></pre>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>