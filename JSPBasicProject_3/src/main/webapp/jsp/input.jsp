<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top : 50px;
}
.row {
	margin : 0 auto;
	widht: 500px;
}
h3 {
	text-align : center;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<h3>사용자정보</h3>
		<form method="get" action="output.jsp">
			<table class="table-content">
			<tbody>
				<tr>
					<th width="20%" class="text-center">이름</th>
					<td width="80%"><input type="text" name=name size=20 class="input-sm"></td>
				</tr>
				<tr>
					<th width="20%" class="text-center">비밀번호</th>
					<td width="80%"><input type="password" name=name size=20 class="input-sm"></td>
				</tr>
				<tr>
					<th width="20%" class="text-center">성별</th>
					<td width="80%">
						<input type=radio name=gender value=여자 checked>
						<input type=radio name=gender value=남자>
					</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">지역</th>
					<td width="80%">
						<select>
							<option>서울</option>
							<option>제주도</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">지역</th>
					<td width="80%">
						<textarea name=content rows="5" columns="5"></textarea>
					</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">생년월일</th>
					<td width="80%">
						<input type="date" name="day">
					</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">취미</th>
					<td width="80%">
						<input type="checkbox" name=hobby value="낚시" checked>낚시
						<input type="checkbox" name=hobby value="그림">그림
						<input type="checkbox" name=hobby value="사진찍기">사진찍기
						<input type="checkbox" name=hobby value="달리기">달리기
					</td>
				</tr>
				<tr colspan="2">
					<button>전송</button>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</div>
</body>
</html>