<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.box{
	width: 200px;
	height: 200px;
	border: 1px solid black;
	margin-top: 2px
}
</style>
<script>
window.onload=()=>{
	let box = document.querySelectorAll(".box")
	for(let i=0;i<box.length;i++){
		box[i].addEventListener('mousedown',function(){
			box[i].style.backgroundColor='red'
		})
		box[i].addEventListener('mouseup',function(){
			box[i].style.backgroundColor='orange'
		})
	}
}
</script>
</head>
<body>
	<div class="box"></div>
	<div class="box"></div>
	<div class="box"></div>
	<div class="box"></div>
	<div class="box"></div>
	<div class="box"></div>
</body>
</html>