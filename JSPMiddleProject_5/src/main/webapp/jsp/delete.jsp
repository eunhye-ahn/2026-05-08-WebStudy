<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel ="stylesheet" href="style.css">
</head>
<body>
<!-- 
현재 파일에서 처리 : ajax/react/vue => 화면 변경없이 그자리에서 처리 
			로그인, 로그아웃, 검색, 페이지 변경, 추천, 결제
 -->
<div class=:container>
	<div class="row" style="width : 300px">
		<h3>삭제하기</h3>
		<form action="delete_ok.jsp" method="post">
		<table class="table">
			<tr>
				<td class="text-center">
					비밀번호:<input type="password" name="pwd" size=15 class="input-sm">
					<input type="hidden" name="no" value="${param.no}">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
				<button class="btn-sm btn-warning">삭제</button>
				<button class="btn-sm btn-warning" type="button" onclick="javascript:history.back()">
				취소
				</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>