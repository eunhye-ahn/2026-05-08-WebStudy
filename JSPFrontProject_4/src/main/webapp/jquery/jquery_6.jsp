<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	이벤트
		=> 고전적인 방식 
			$().click(function(){})
			$().keydown(function(){})
			$().keyup(function(){})
			$().change(function(){})
			---------------------------
			$().mouseup(function(){})
			$().mouseover(function(){})
			---------------------------
			$().mouseover(function(){})
			$().mouseout(function(){})
			-----------------------------$().hover(function(){})
		=> 리스너 이용 방식  -jquery4에서 주로사용
			$().on('click',function(){})
			
			
			
			사용자 => 이벤트 : 처리 => DOMScript => 라이브러리 : jquery
			태그를 제어하는 프로그램 (조작)
			속성/스타일/데이터 변경
			
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 600px;
}
h1 {
   text-align: center;
}
#chatArea{
	width: 300px;
	height: 300px;
	overflow-y: auto;
	border: 1px solid black; 
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#sendMsg').on('keydown',function(key){
		if(key.keyCode == 13){
			let msg = $(this).val()
			if(msg.trim() === ""){
				$(this).focus()
				return
			}
			$('#recMsg').append(msg+"<br>")
			$(this).val('')
			$(this).focus()
			
			//스크롤바 조정
			let ch=$('#chatArea').height()
			let m=$("#recMsg").height()-ch
			$('#chatArea').scrollTop(m)
			console.log(ch)
		}
	})
})
</script>
</head>
<body>
<div class="container">
	<div class="row">
		<h1>실시간 채팅</h1>
		<table class="table">
			<tr>
				<td>
					<div id="chatArea">
						<div id="recMsg"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" size=35 id="sendMsg">
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>