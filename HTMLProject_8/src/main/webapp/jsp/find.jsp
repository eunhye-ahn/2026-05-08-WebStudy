<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*,com.sist.dao.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
 .container {
   margin-top: 50px;
 }
 .row {
   /* 화면에 출력 */
   margin: 0px auto;
   width:960px;
 }
 p {
     overflow: hidden;		/*벗어난 글자 감추기 -> css깨짐방지*/
     white-space: nowrap;	/*한줄로만들기*/
     text-overflow: ellipsis; /*넘치는 것 ...처리*/
 }
</style>
</head>
<body>
	<div class="container">
		<div class="row text-center">
			<form action="find.jsp" method="post"> <%-- 데이터 묶어서 보내기 : select/input/textarea만 보낼 수 있음 --%>
				<select name="column" class="input-sm">
					<option value="address">주소</option>
					<option value="type" selected>음식종류</option>
					<option value="name">업체명</opiton>
				</select>
				<input type="text" size="20" name="fd" class="input-sm" required>
				<button class="btn-sm btn-info">검색</button>
			</form>
		</div>
	</div>
</body>
</html>