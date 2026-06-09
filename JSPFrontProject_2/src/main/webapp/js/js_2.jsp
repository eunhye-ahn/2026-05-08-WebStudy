<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

//스타일조작
window.onload=()=>{
	//문서 객체 가져오기
	let h2=document.querySelector("h2")	//Selector : 처음 1개만 선택
	h2.style.color="blue"
	let h3=document.querySelector("h3")
	h3.style.color="green"
	
	let h1 = document.querySelectorAll("h1")	// SelectorAll 여러개동시선택 => []
	//h1[1].style.color="orange"
	//h1[0].style.color="pink"
	let colors = ['pink', 'orange']
	for(let i=0;i<h1.length;i++){
		h1[i].style.color=colors[i]
	}
	let h4_1 = document.getElementById("h4")
	let h4_2 = document.getElementsByClassName("h4")
	let h4_3 = document.getElementsByName("h4")
	h4_1.style.background="red"
	h4_2[0].style.background="orange"
	h4_3[0].style.background="yellow"
	
	/*
	document.getElementById
	document.querySelector			//jquery : $("#ID명")
	---------------------------1개만
	document.getElementsByName
	document.getElementsByClassName
	document.getElementsByTagName
	document.querySelectorAll
	---------------------------여러개 => [] : 출력할때 배열처리 필요
	*/
}
</script>
</head>
<body>
<h1>Hello DOM!-1</h1>
<h2>Hello DOM!</h2>
<h3>Hello DOM!</h3>
<h1>Hello DOM!-2</h1>
<h4 id="h4">Hello DOM! -ID</h4>
<h4 class="h4">Hello DOM! -class</h4>
<h4 name="h4">Hello DOM! -name</h4>
</body>
</html>