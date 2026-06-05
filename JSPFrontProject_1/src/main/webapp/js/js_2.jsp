<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트에서 지원하는 연산자
	1)단항연산자
	=> 증감연산자(++,--)
	=> 부정연산자(!)
	=> 형변환연산자
		Number(), parseInt()
		Boolean()
		String()
		let a=(int)10.5 X
	2)이항연산자
	=> 산술연산자 : + - * / %
	=> 비교연산자 : == != < > <= >=
				------ ===, !== (데이터형이 같은경우)
	=> 논리연산자 : && ||
	=> 대입연산자 : = += -= *= ....
	3) 삼항연산자
		조건? 값1 : 값2
		
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--
<script class="text/javascript">
	window.onload=function(){
		//단항연산자
		let a = 10
		document.write("<h1> 증가전:"+a+"</h1>")
		a++
		document.write("<h1> 증가후:"+a+"</h1>")
		document.write("<h1> 감소전:"+a+"</h1>")
		a--
		document.write("<h1> 감소후:"+a+"</h1>")
		document.write("<hr>")
		a= 10
		let b= a++
		document.write("<h1>a:"+a+"</h1>")
		document.write("<h1>b:"+b+"</h1>")
		a= 10
		b= ++a
		document.write("<h1>a:"+a+"</h1>")
		document.write("<h1>b:"+b+"</h1>")
		//document => 화면을 출력하는 브라우저 영역
		
		let c= true;
		document.write("변경전 c:"+c+"<br>")
		c=!c
		document.write("변경후 c:"+c+"<br>")
		
		c= 0;	//0,0.0 아닌 수는 true
		document.write("변경전 c:"+c+"<br>")
		c=!c	//true/false를 출력하는 연산자 => 자동으로 boolean으로 변경
		document.write("변경후 c:"+c+"<br>")
		
		document.write("<hr>")
		
		let a1=10
		document.write("a1:"+a1+", type:"+typeof a1+"<br>")
		al=String(a1)
		document.write("a1:"+a1+", type:"+typeof a1+"<br>")
		al=Number(a1)
		document.write("Boolean(1):"+Boolean(a1)+"<br>")
		document.write("Boolean(0.0):"+Boolean(0.0)+"<br>")
		document.write("Boolean(0):"+Boolean(0)+"<br>")
		document.write("Boolean():"+Boolean("Java")+"<br>")
		document.write("Boolean():"+Boolean("")+"<br>")
	}
</script>
 --%>
 <script>
 	function calc(){
 		let f= document.getElementById("first").value
 		let s= document.getElementById("second").value
 		
 		console.log("f="+f+", type:"+typeof f)
 		console.log("s="+s+", type:"+typeof s)
 		//태그가져오기
 		let span = document.getElementById("result")
 		//태그안에 값넣기
 		span.innerHTML ="<h2>"+(Number(f)+Number(s))+"</h2>"
 	}
 </script>
</head>
<body>
	첫번째값: <input type=text id="first" size=10><br>
	두번째값: <input type=text id="second" size=10><br>
	<input type=button value="계산" onclick="calc()">
	<span id="result"></span>
</body>
</html>