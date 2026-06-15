<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	removeClass / addClass
		| 태그삭제/추가
	<a class="">
	=> 메뉴에 주로 사용
	
	태그는 사용자 정의가 없다 / 속성은 사용자정의가 가능하다
	예약 / 결제 => 사용자정의 속성을 이용한다
				-------------- data-
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
*{
	margin: 0px;
	padding: 0px;
	box-sizing: border-box;
	
}
.menu{
	display: flex;	/* 이미지 카드, grid */
	list-style: none;
	background-color: #333;
}
.menu li{
	padding: 15px 25px;	/* 15 25 15 25 : top right bottom left*/
	color: white;
	cursor: pointer;
}
.menu li.active{
	background: #2196F3;
}
.content{
	display: none;
	padding: 20px;
	border: 1px solid #ddd;
}
.content.active{
	display: block;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-4.0.0-rc.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.menu li').on('click',function(){
		$('.menu li').removeClass('active')
		$(this).addClass('active')
		
		$('.content').removeClass('active')
		
		const tab=$(this).attr('data-tab')
		$('#tab'+tab).addClass('active')
		
	})
})
</script>
</head>
<body>
<ul class="menu">
	<li class="active" data-tab="1">홈</li>
	<li data-tab="2">회원</li>
	<li data-tab="3">묻고답하기</li>
</ul>
	<div id="tab1" class="content active">
		홈 메뉴입니다
	</div>
	<div id="tab2" class="content">
		회원관련 메뉴입니다
	</div>
	<div id="tab3" class="content">
		묻고답하기 메뉴입니다
	</div>
</body>
</html>