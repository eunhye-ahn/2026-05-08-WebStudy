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
	$('.reserves').on('click',function(){
		const rno = $(this).attr('data-rno')
		$.ajax({
			method:'post',
			url:'../mypage/reserve_info.do',
			data:{rno},
			success:function(res){
				const json = JSON.parse(res)
				console.log(json)
				$('#img').attr('src',json.fvo.poster)
				$('#name').text(json.fvo.name)
				$('#score').text(json.fvo.score)
				$('#rno').text(json.rno)
				$('#rdate').text(json.rdate)
				$('#rtime').text(json.rtime)
				$('#inwon').text(json.inwon)
				$('#address').text(json.fvo.address)
				$('#phone').text(json.fvo.phone)
				$('#type').text(json.fvo.type)
				$('#parking').text(json.fvo.parking)
				$('#time').text(json.fvo.time)
				$('.info').show()
				
				//clearTimeout()
				setTimeout(()=>{
					$('.info').hide("slow")
				},3000)
			}
		})
	})
})
</script>
</head>
<body>
<main class="mypage-main">
    <h2>예약 정보</h2>
    <table class="table">
    	<tr>
    		<th width="10%">번호</th>
    		<th width="10%"></th>
    		<th width="20%">맛집명</th>
    		<th width="20%">예약일</th>
    		<th width="10%">시간</th>
    		<th width="10%">인원</th>
    		<th width="20%"></th>
    	</tr>
    	<c:forEach var="vo" items="${list }">
	    	<tr>
	    		<td width="10%">${vo.rno }</td>
	    		<td width="10%"><img src="${vo.fvo.poster }" style="width:35px;height:35px"></td>
	    		<td width="20%">${vo.fvo.name }</td>
	    		<td width="20%">${vo.rdate }</td>
	    		<td width="10%">${vo.rtime }</td>
	    		<td width="10%">${vo.inwon }</td>
	    		<td width="20%">
	    			<c:if test="${vo.ok=='N' }">
	    				<span class="btn btn-xs btn-default">대기</span>
	    				<a href="../reserve/reserve_delete.do?rno=${vo.rno }" class="btn btn-xs btn-warning">취소</a>
	    			</c:if>
	    			<c:if test="${vo.ok=='Y' }">
	    				<span class="btn btn-xs btn-danger reserves" data-rno="${vo.rno }">완료</span>
	    			</c:if>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
    <table class="info table" style="display:none">
    	<tr>
    		<td width="40%" rowspan="10" class="text-center">
    			<img src="" id="img" style="width:340px;height: 350px" class="rounded">
    		</td>
    		<td colspan="2">
    			<h3><span id="name"></span>&nbsp;<span id="score" style="color:orange"></span></h3>
    		</td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">예약번호</th>
    		<td width="45%" id="rno"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">예약일</th>
    		<td width="45%" id="rdate"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">예약시간</th>
    		<td width="45%" id="rtime"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">예약인원</th>
    		<td width="45%" id="inwon"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">주소</th>
    		<td width="45%" id="address"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">전화</th>
    		<td width="45%" id="phone"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">음식종류</th>
    		<td width="45%" id="type"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">주차</th>
    		<td width="45%" id="parking"></td>
    	</tr>
    	<tr>
    		<th width="15%" style="color:gray">영업시간</th>
    		<td width="45%" id="time"></td>
    	</tr>
    </table>
</main>
</body>
</html>