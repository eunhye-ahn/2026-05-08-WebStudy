<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	배열 : 여러개의 데이터를 모아서 한개의 이름으로 제어
			=> 인덱스번호를 이용해서 데이터를 관리한다
			=> object : 데이터형이 섞일 수 있다
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	/*
	자바스크립트의 데이터형 : number, 
	*/
	
	let arr = []
	console.log("arr=" + typeof arr)
	let names = ["홍길동","홍길둘","홍길삼","홍길사"]
	//1.인덱스를 이용한 방식으로 데이러읽기 = 0번부터시작
	document.write("<h1>인덱스사용</h1>")
	document.write("<ul>")
	document.write("<li>"+names[0]+"</li>")
	document.write("<li>"+names[1]+"</li>")
	document.write("<li>"+names[2]+"</li>")
	document.write("<li>"+names[3]+"</li>")
	document.write("</ul>")
	//2.forEach를 이용한 방식으로 데이터읽기
	document.write("<hr>")
	document.write("<h1>for of사용</h1>")
	document.write("<ul>")
	for(let name of names){
		document.write("<li>"+name+"</li>")	
	}
	document.write("</ul>")
	document.write("<hr>")
	//가장 많이 사용
	document.write("<h1>for each 사용</h1>")
	document.write("<ul>")
	names.forEach(name=>{
		document.write("<li>"+name+"</li>")	
	})
	//=>function/return 제외 => 람다
	document.write("</ul>")
	document.write("</ul>")
	document.write("<hr>")
	document.write("<h1>map 사용</h1>")
	document.write("<ul>")
	names.map(name=>{
		document.write("<li>"+name+"</li>")	
	})
	
	document.write("</ul>")
	
	/*
	
	*/
	
}
</script>
</head>
<body>

</body>
</html>