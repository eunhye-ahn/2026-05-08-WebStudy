<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	문서객체 (DOM) 생성 / 추가
		**append : 내부 뒤에 추가
		prepend : 내부 앞에 추가
		after : 다음에 추가
		
	431page 중요
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.box{
	border : 2px dashed #ccc;
	padding : 15px;
	margin-top: 10px;
}
.item{
	color: blue;
	font-weight: bold;
}
.high{
	background-color: yellow;
	padding: 5px
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
//전역변수
let count=1
$(function(){
	$("#appendBtn").on('click',function(){
		let newItem = $('<li class="item">New Item '+count+'</li>')
		$('#list').append(newItem)
		count++
	})
	$("#prependBtn").on('click',function(){
		let newItem = $('<li class="item">New Item '+count+'</li>')
		$('#list').prepend(newItem)
		count++
	})
	$("#afterBtn").on('click',function(){
		let newText = $('<p style="color:red">외부에추가</p>')
		$('#target_box').after(newText)
	})
})
</script>
</head>
<body>
	<h3>동적객체(태그) 생성</h3>
	<button id="appendBtn">append</button>
	<button id="prependBtn">prepend</button>
	<button id="afterBtn">after</button>
	<div class="box" id="target_box">
		<p class="high">박스영역</p>
		<ul id="list">
			<li>기존 아이템</li>
		</ul>
	</div>
</body>
</html>