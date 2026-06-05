<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
	window.onload=function(){
		let a=5
		let b=2
		console.log("a+b="+(a+b))	//7
		console.log("a-b="+(a-b))	//3
		console.log("a*b="+(a*b))	//10
		console.log("a/b="+(a/b))	//2.5
		console.log("a%b="+(a%b))	//1
		
		let c= "A"
		let f= 2000000
		console.log(c+f)	//문자열결합
		
		console.log(parseInt(c)+f)	//NAN
		
		let m =10
		let n ="10"
		console.log("m==n:"+(m==n))
		console.log("m===n:"+(m===n))
		console.log("m!=n:"+(m!=n))
		console.log("m!==n:"+(m!==n))
		console.log("m>=n:"+(m>=n))
		console.log("m<=n:"+(m<=n))
	}
	*/
	function ok(){
		let frm = document.frm
		if(frm.id.value === ""){
			alert("아이디를 입력하세요")
			return
		}
		if(frm.pwd.value === ""){
			alert("비번을 입력하세요")
			return
		}
		if(frm.pwd1.value === ""){
			alert("비번를 재입력하세요")
			return
		}
		if(frm.pwd.value !== frm.pwd1.value){
			alert("비번이 틀립니다")
			return
		}
	}
</script>
</head>
<body>
<form name="frm">
	ID:<input type="text" name="id" size="15">
	PWD:<input type="password" name="pwd" size="15">
	재확인:<input type="password" name="pwd1" size="15">
	<input type="button" value="확인" onclick="ok()">
</form>
</body>
</html>