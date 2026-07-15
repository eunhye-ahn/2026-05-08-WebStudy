<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
$(function(){
	$('.inwons').on('click',function(){
		const inwon = $(this).text()
		$('#food_inwon').text(inwon)
		$('#reserveBtn').show()
		$('#rinwon').val(inwon)
	})
})
</script>
<body>
<c:forEach var="i" begin="2" end="5">
	<button class="btn-outline-danger btn-sm inwons">${i}명</button>
</c:forEach>
<button class="btn-outline-danger btn-sm inswons">단체</button>
</body>
</html>