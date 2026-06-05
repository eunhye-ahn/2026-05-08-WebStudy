<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	일반 for
	for(초기값;조건식;증가식){
		반복수행문장
	}
	
	데이터가 집합일 경우 : 배열 / 객체
	for~each 이용
	for(BoardVO vo : list) => 목록 출력
	1. for in
	for(변수 in 배열){
		==> 배열의 인덱스 번호를 가지고 온다
		==> 배열 여러개를 동시에 수행할 때
	}
	2. for of
	for(변수 of 배열){
	변수는 실제 배열 안의 값을 가져옴
	}
	3. forEach
	배열.forEach(function(변수){})
	4. map
	배열.map(function(변수){
	})
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload=()=>{
	let names = ["홍길동","박길동","김길동"]
	let genders = ["남자","여자","남자"]
	
	document.write("<h1>회원목록</h1>")
	document.write("<ul>")
	for(let index in names){
		document.write("<li>"+names[index]+"("+genders[index]+")"+"</li>")
	}
	document.write("</ul>")
	document.write("<hr>")
	document.write("<h1>회원이름(for of)</h1>")
	for(let name of names){
		document.write("<h3>"+name+"</h3>")
	}
	document.write("<hr>")
	document.write("<h1>회원이름(for each)</h1>")
	names.forEach(function(name,index){
		document.write("<h3>"+(index+1)+"."+name+"</h3>")
	})
	document.write("<hr>")
	document.write("<h1>회원이름(map)</h1>")
	names.map(function(name, index){
		document.write("<h3>"+(index+1)+"."+name+"</h3>")
	})
	document.write("<hr>")
	document.write("<h1>회원이름(for each)</h1>")
	names.forEach((name,index)=>{
		document.write("<h3>"+(index+1)+"."+name+"</h3>")
	})
	document.write("<hr>")
	document.write("<h1>회원이름(map)</h1>")
	names.map((name, index)=>{
		document.write("<h3>"+(index+1)+"."+name+"</h3>")
	})
}
</script>
</head>
<body>

</body>
</html>