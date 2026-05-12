<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*, com.sist.vo.*"%>
<%--
   JSP(Java Server Page) : 서버에서 실행되는 파일
   장점 : 자바 + HTMl
   		----   ----
   		 |      |
   		 --------
   		     |
   		   자바 : <% %>
   		   		------ 스크립트릿 <% %> : 일반자바 -변수선언/메소드 호출/제어문
   		   		------  표현식   <%= %> : println() => 화면 변수값 출력
   		   		------  선언식   <%! %> : 사용빈도가없다, 멤버변수 / 메소드선언
   		HTML : 정적페이지 
   		JSP : 동적페이지
   		
   		동장방식
   			브라우저에서 요청 : URL => sawon.jsp
   			tomcat -> 자바파일로 변경 : servlet -> 컴파일 -> .class -> 실행 => HTML만 메모리에 저장 => 브라우저에서 읽어감
   			
   	   지시자 : page => 파일정보 => 속성 : import => 다른파일 불러오기
   	   
   	   1.자바에서 데이터수집
   	   2.HTML을 이용해서 데이터 출력
   	   3.CSS 이용해서 화면 UI
--%>			
<%
	EmpDAO dao = new EmpDAO();
	List<EmpVO> list = dao.emp_list();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
	/* 가운데정렬 : Box-Model */
	margin: 100px auto;
	border-collapse: collapse;
	width: 700px;
	border-radius : 8px;
	box-shadow: 0 2px 10px rgba(0,0,0,0.5);
}
thead{
	background-color: #62DE7C;
	color: white;
}
th,td{
	/*글자간격조절*/
	padding: 12px 16px;
	/*정렬*/
	text-align: center;
}
tbody tr{
	border-bottom: 1px solid #eee;
}
tbody tr:hover{
	background-color: #E5FF06; 
}
tbody tr:nth-child(2n){
	background-color: #FFAEC9;
}
tbody tr:last-child{
	border-bottom: none; 
}
</style>
</head>
<body>
	<table id="table_content">
		<thead>
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>직위</th>
				<th>입사일</th>
				<th>급여</th>
			</tr>
		</thead>
		<tbody>
			<% 
				for(EmpVO vo: list){
			%>
			<tr>
				<td><%= vo.getEmpno() %></td>
				<td><%= vo.getEname() %></td>
				<td><%= vo.getJob() %></td>
				<td><%= vo.getHiredate() %></td>
				<td><%= vo.getSal() %></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>