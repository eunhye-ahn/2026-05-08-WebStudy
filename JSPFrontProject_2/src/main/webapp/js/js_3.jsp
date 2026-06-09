<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <style>
 .conatiner{
 	margin-top: 50px;
 }
 .row{
 	margin: 0 auto;
 	widht: 350px;
 }
 </style>
 <script type="text/javascript">
 
 //속성조작
	 let index = 1
	 let prev =()=>{
		 index--
		 if(index <1){
			 index=7
		 }
		 let img = document.querySelector("img")
		 let path="../images/m"+index+".jpg"
		 img.src=path
	 }
	 let next =()=>{
		 index++
		 if(index >7){
			 index=1
		 }
		 let img = document.querySelector("img")
		 let path="../images/m"+index+".jpg"
		 img.src=path	//태그의 속성변경 => 제어 => m1 m2 m3 .... 이미지 이름이 틀린 경우 => []
	 }
	 //지정된시간마다 함수호출 => 실시간뉴스/날씨 => 자바 : Thread.sleep(1000)
	 //1000ms => 1초
	 let auto=()=>{
		 setInterval(()=>{
			 next()
		 },1000)
	 }
 </script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-center">
						<img src="../images/m1.jpg" style="width:320px;height:420px;object-fit:cover">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<button type="button" class="btn-sm btn-warning" onclick="prev()">이전</button>
						<button type="button" class="btn-sm btn-info" onclick="next()">다음</button>
						<button type="button" class="btn-sm btn-success" onclick="auto()">자동</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>