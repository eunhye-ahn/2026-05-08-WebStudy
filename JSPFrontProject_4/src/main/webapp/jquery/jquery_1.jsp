<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	Jquery 3 => Jquery 4 
		1. 라이브러리가 작아졌다 => 실행속도 최적화
		2. 자바스크립트 라이브러리 => 프레임워크 형식 (소스 통일화)
								----------- ??
		3. 과거 : Spring MyBatis Jquery3
			현재 : SpringBoot(NodeJS) JPA React(Vue)
			
			web2.0 : Ajax / axios / fetch
			web3.0 : 블록체인 => 개인컨텐츠(개인정보 중요)
			----------------------------------------
			C/S => Cloud => 예측/기반 => 자동화	=> 감성 컴퓨팅 
							빅데이터		AI
			AI => 단점 : 학습비용 => 회사보안프로글매에 접근X
		
		태그, 속성을 제어하는 프로그램 : 문서 객체 모델 (DOM)
		DomScript 
		=> 속성조작/문자조작/스타일조작/이벤트가능
		=> document.querySelector("#btn")
		=> document.querySelectorAll("#btn")
		=> document.getElementById("btn")
		=> document.getElementsByClassName("btn")
		--------------------------------------------통일 => $
		$('CSS선택자') :모든 태그 읽기 가능
		----------------
		조작
		1.문자조작
		textContent, innerHTML
			|				|
		text()			html()	:함수로 변화
		2.스타일조작
		태그.style.속성명 => css()
		btn.style.color="red" 
		=> $("#btn").css("color","red")
		btn.style.backgroundColor="yellow"
		=>$("#btn").css("backgroundColor","yellow")
		
		4버전
		$("#btn").css({
			"color":"red",
			"backgroundColor": "yellow"
		})
		3.속성조작
		let img = document.querySelector("img")
		img.src=""
		=>$("img").attr("src","")
		
		값읽기
		<input type="text" value="aaa">
		let input = document.querySelector("aaa")
		input.value=""
		=> $("input").val("")
		
		**getter/setter
			text() : getter / text("aaa") : setter
			attr("src")			/ attr("src","")
			val()	/ val("")
			html()	/html("")
		----------------------------------------------
		이벤트
		 <img>
		 	=> 
		 	let img = querySelector("img")
		 	img.addEventListener('click',function(){})
		 	
		 	$('img').on('click',function(){})
		 	$('img').click(function(){})
		 	
		기타
			서버와 연결
				ajax => $.ajax({})
				=> vue/react : fetch/axios
				
		***jquery는 버전 충돌시 작동이 안됨
		main.jsp => 자바스크립트 라이브러리 추가
			
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
//window.onload=()=>{}
/*
$(document).ready(function(){
	
})
*/
//jquery 3,4 동일
$(function(){
	//스타일조작
	$('#h1').css('color','yellow')
	$('.h1').css('backgroundColor','lightblue')
	$('#h2').css('color','red')
	$('#h3').css('color','blue')
	//같은태그구분 => nth-child() x eq() o
	$('h2:eq(0)').css('color','pink')
				 .css("backgroundColor","black")
	$('h2:eq(1)').css({
		"color" :"orange",
		"backgroundColor" : "yellow"
	})
	$('h2:eq(2)').css('color','magenta')
	$('h2:eq(3)').css('color','gray')
	$('h2:eq(4)').css('color','blue')
	//$('h3').eq(0).css('color', 'pink')
	/*
	let h3s = document.querySelectorAll("h3")
	for(let h3 of h3s){
		h3.style.color="white"
		h3.style.backgroundColor="blue"
	}
	*/
	$('h3').css({
		'color':'white',
		'backgroundColor':'blue'
	})
	$('img').css({
		'width':'160px',
		'height':'230px'
	})
	//이벤트 if else 처리 (function,function)
	$('img').hover(function(){
		//이벤트가 발생한 것 : this
		$(this).css({
			'cursor':'pointer',
			'border':'3px solid green'
		})	
	},function(){
		$(this).css({
			'cursor':'none',
			'border':'none'
		})
	})

})
</script>
</head>
<body>
	<h1 id="h1">HelloJquery</h1>
	<h1 class="h1">HelloJquery</h1>
	<h1 class="h1">HelloJquery</h1>
	<h1 id="h2">HelloJquery</h1>
	<h1 id="h3">HelloJquery</h1>
	<h2>HelloJquery</h2>
	<h2>HelloJquery</h2>
	<h2>HelloJquery</h2>
	<h2>HelloJquery</h2>
	<h2>HelloJquery</h2>
	<h3>HelloJquery</h3>
	<h3>HelloJquery</h3>
	<h3>HelloJquery</h3>
	<h3>HelloJquery</h3>
	<h3>HelloJquery</h3>
	<img src="https://img.megabox.co.kr/SharedImg/2026/05/13/zlk6dycnwuPdVuSH40RVickv2CYYCrcU_420.jpg">
	<img src="https://img.megabox.co.kr/SharedImg/2026/05/19/Xya2c40b4Yck7jNWa2l0NWUpIuoZkPAV_420.jpg">
	<img src="https://img.megabox.co.kr/SharedImg/2026/05/21/ysq28y1C9Y3yJdnIIwb3kacb7Sx6omLV_420.jpg">
	<img src="https://img.megabox.co.kr/SharedImg/2026/06/09/KohW1eBmK8OyASrtmtTWAK51aqk448re_420.jpg">
	<img src="https://img.megabox.co.kr/SharedImg/2026/05/27/AodTyXKohEi0aRnhy5MC7kHn3yO8Ixem_420.jpg">
</body>
</html>