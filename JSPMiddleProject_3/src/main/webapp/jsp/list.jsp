<%@page import="com.sist.model.MusicModel"%>
<%@page import="com.sist.dao.MusicVO"%>
<%@page import="com.sist.dao.MusicDAO,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//사용자 요청정보받기
String strpage = request.getParameter("page");
if(strpage == null){
	strpage = "1";
}
int curpage = Integer.parseInt(strpage);

//db연결
MusicDAO dao = MusicDAO.newInstance();
List<MusicVO> list = dao.musicListData(curpage);
int totalpage = 10;

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
 	widht: 1024px;
 }
 h3 {
 	text-align : center;
 }
 </style>
</head>
<body>
<div class="container">
	<div class="row">
		<h3>뮤직 top 200</h3>
		<table class="table">
			<tr class="danger">
				<th class="text-center">순위</th>
				<th class="text-center"></th>
				<th class="text-center"></th>
				<th class="text-center">곡명</th>
				<th class="text-center">가수명</th>
				<th class="text-center">앨범</th>
			</tr>
			<!-- 데이터출력 -->
			<%
			for(MusicVO vo : list){
			%>
			<tr>
				<td><%=vo.getNo() %></td>
				<td>
				<%
				String s = "-";
				if(vo.getState().equals("상승")){
					s="<font color='red'>▲ "+vo.getIdcrement()+"</font>";
				}
				else if(vo.getState().equals("하강")){
					s="<font color='blue'>▼ "+vo.getIdcrement()+"</font>";
				}
				%>
				<%=s %>
				</td>
				<td>
					<img src="<%=vo.getPoster()%>" width="30" height="30">
				</td>
				<td><%=vo.getTitle() %></td>
				<td><%=vo.getSinger() %></td>
				<td><%=vo.getAlbum() %></td>
			</tr>
			<%
			}
			%>
		</table>
		<table class="table">
			<tr>
				<td class="text-center">
					<a href="list.jsp?page=<%=curpage>1? curpage-1:curpage %>" class="btn btn-sm btn-primary">이전</a>
					<%=curpage %>page / <%=totalpage %>pages
					<a href="list.jsp?page=<%=curpage<totalpage? curpage+1:curpage %>" class="btn btn-sm btn-primary">다음</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>