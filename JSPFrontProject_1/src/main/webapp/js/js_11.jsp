<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	배열함수
		**추가 : push => 데이터 마지막에 추가
		삭제 : pop => 마지막 삭제  
		개수 : length 
		***자르기 : javascript에서 페이징 slice(start, end) 
		찾기 : find()
		배열복사 : 스코프 연산자 ... => react
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=>{
	let names = ["홍길동","홍길둘","홍길삼","홍길사"]
	document.write("<h3>원본배열</h3>")
	document.write("<ul>")
	names.map(name=>{
	document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names.length+"</li>")
	document.write("</ul>")
	
	names.push("홍길오")
	
	document.write("<hr>")
	document.write("<h3>배열추가:push</h3>")
	document.write("<ul>")
	names.map(name=>{
	document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names.length+"</li>")
	document.write("</ul>")
	
	//names.pop() //=> 마지막 데이터 삭제
	//names.shift() //=> 가장 처음에 있는 데이터 삭제
	document.write("<hr>")
	document.write("<h3>배열삭제:pop</h3>")
	document.write("<ul>")
	names.map(name=>{
	document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names.length+"</li>")
	document.write("</ul>")
	
	
	document.write("<hr>")
	document.write("<h3>배열자르기:slice(stat,end):새로운배열생성</h3>")
	let names2 = names.slice(2)	//인덱스번호 2번부터~
	document.write("<ul>")
	names2.map(name=>{
	document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names2.length+"</li>")
	document.write("</ul>")
	
	document.write("<hr>")
	document.write("<h3>배열자르기:slice(stat,end):새로운배열생성</h3>")
	let names3= names.slice(1,3)	//인덱스번호 1,2
	document.write("<ul>")
	names3.map(name=>{
	document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names3.length+"</li>")
	document.write("</ul>")
	
	document.write("<hr>")
	document.write("<h3>배열복사:...</h3>")
	let names4= [...names,"홍길육"]	
	document.write("<ul>")
	names4.map(name=>{
	document.write("<li>"+name+"</li>")
	})
	document.write("<li>총인원:"+names4.length+"</li>")
	document.write("</ul>")
}
</script>
</head>
<body>

</body>
</html>