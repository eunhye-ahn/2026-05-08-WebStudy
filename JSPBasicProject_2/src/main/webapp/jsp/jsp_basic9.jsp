<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body > div {
    width: 400px;
    height: 300px;
    border: 3px solid black;
    position: relative;  /* 자식들의 absolute 기준점 */
    overflow-y: scroll;
}
.d {
    width: 100px;
    height: 100px;
    position: absolute;  /* 부모(body>div) 기준으로 위치 */
}
.a {
    background-color: yellow;
    left: 10px;
    top: 10px;   /* 부모 기준 왼쪽10 위쪽10 */
}
.b {
    background-color: blue;
    left: 150px;
    top: 50px;
}
.c {
    background-color: red;
    left: 280px;
    top: 100px;
}
</style>
<body>
	<div>
		<div class="d a"></div>
		<div class="d b"></div>
		<div class="d c"></div>
	</div>
</body>
</html>