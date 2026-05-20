<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">JSP Script</h1>
			<table>
				<tbody>
					<tr>
						<th>&lt;% %&gt;</th>
						<td>일반 자바소스</td>
						<td>_javaService():스크립트릿</td>
					</tr>
					<tr>
						<th>&lt;%= %&gt;</th>
						<td>브라우저 출력</td>
						<td>_javaService() => out.print()로 변경:표현식</td>
					</tr>
					<tr>
						<th>&lt;%! %&gt;</th>
						<td>메소드,전역변수 설정</td>
						<td>class블록:선언식</td>
					</tr>
					<tr>
						<td colspan="2">자바와 HTML 구분할때 사용</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>