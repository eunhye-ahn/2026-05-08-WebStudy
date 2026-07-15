<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$(".times").on('click',function(){
		const time = $(this).text()
		$('#food_time').text(time)
		$('#rtime').val(time)
		
		$.ajax({
			method:'post',
			url:'../reserve/reserve_inwon.do',
			success:function(res){
				$('#food_inwons').html(res)
				
			}
		})
	})
})
</script>
</head>
<body>
<c:forEach var="time" items="${times }">
	<button class="btn-outline-primary btn-sm times" class="times">${time}</button>
</c:forEach>
</body>
</html>