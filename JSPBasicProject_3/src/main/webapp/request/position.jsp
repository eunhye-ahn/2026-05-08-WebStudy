<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
position

static: html의 기본 문서흐름 top/right/left/bottom 적용X
absolute: 가장가까운태그를 기준으로 설정
relative: 고정좌표 -> 스크롤시에 영향X
fixed: 스크롤시에만 고정 


부모요소는 relative인게 관례
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.box{
	width:100px;
	height:100px;
	top: 100px;
	left: 100px;
}
.static{
	position: static;
	background-color: yellow;

}
.relative{
	position: relative;
	background-color: orange;
}
.absolute{
	position: absolute;
	background-color: green;
}
.container{
	position: relative;
	width: 100%;
	height: 300px;
	border: 1px solid gray;
	overflow-y: scroll;
}
.fixed{
	position:fixed;
	background-color: red;
}
.sticky{
	position:sticky;
	background-color: purple;
}
.scroll-area{
	width: 500px;
	height: 500px;
}
</style>
</head>
<body>
	<h1>CSS 위치속성</h1>
	<h2>1.static (기본)</h2>
	<div class="container">
		<div class="box static"><p>문서의 기본 흐름에 따라 배치</p></div>
	</div>
	<div class="container">
		<div class="box relative"><p>원래자리에서 기준 좌표조절</p></div>
	</div>
	<div class="container">
		<div class="box absolute"><p>가까운 태그에서 기준 좌표조절</p></div>
	</div>
	<div class="container">
		<div class="box fixed"><p>스크롤시 고정</p></div>
	</div>
	<div class="container">
		<div class="box sticky"><p> 고정</p></div>
		<div class="scroll-area"></div>
	</div>
</body>
</html>