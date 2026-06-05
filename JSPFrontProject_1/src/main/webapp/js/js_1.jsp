<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트(FE)
	1) 변수 (let(변수),const(상수))
	2) 자료형 종류 (number, string, boolean, null, undefined, 
				function, object)
	3) 연산자
	4) 제어문 (조건문/반복문/반복제어문)
	5) 함수
	6) 배열 / 객체 => JSON
	------------------------------------------- 기본문법
	7) 태그제어 (html 화면제어) => DOM
	8) 이벤트
	9) 동기화/비동기화(async/await)=>Promise
	-------------------------------------------- 바닐라JS(라이브러리없이 순수 JS)
	10) 라이브러리 (JQuery3)
	11) Vue3 => Vuex => Pinia => NuxtJS
	======> NodeJS / TypeScript
	12) React => Redux => TanstackQuery => NextJS 
	
	
	웹구성
	-HTML : 웹페이지 구조
	-CSS : 디자인/스타일 변경
	-JavaScript: 동작/기능 처리 => 데이터 처리
	=> 전체기반 : HTML (브라우저에서 실행하는 언어)
	
	특징
	인터프리터 (컴파일X) : 한줄씩 읽어서 출력 => 에러처리가 어렵다 (실행 전 에러 확인불가)
	동적페이지 : 화면변경 없이 그 자리에서 처리
	비동기처리가 가능 
	
	사용처
	웹개발(동적처리)
	-> 로그인처리 팝업창 아이디중복체크 우편번호검색
	= BackEnd 개발이 가능 (서버) => NodeJS
	= 모바일 ReactNative
	= 데스크탑 애플리케이션 : VsCode / VuErd
	= 게임개발 / 채팅 / 화상 / AI
	
	어떤언어 : HTML/CSS로 구성된 웹페이지를 동적으로 변경하는 역할
				HTML/CSS 정적페이지 (데이터변경X)
	동작 방법
	- 내부 script
		<head>
			<script>
				처리
			</script>
		</head>
	- 외부 script
		.js파일 생성 후 처리
		<script src="파일명">
		</script>
	- 인라인 script
		태그자체처리
		<button onclick="javacript:history.back()">
		</button>
	
	=> JavaScript
		=window.onload = function(){처리} 				=> 변수선언/제어문/함수 선언  / 이벤트등록
		=jquery : $(fuction{})		
		=vue : mounted(){}
		=react : componentDidMount(){}
				  ---------------------useEffect()
				  
	정의 : 웹브라우저에서 동작하는 스크립트 언어
	용도 : 동적페이지 제작 / 서버개발 / 앱 개발
	실행환경 : 브라우저에서 실행
	특징 : 인터프리터 / 객체기반 / 이벤트중심 
	
	1.기본문법
		=변수 : 자동인식변수 => ESS : var / ES6 : let / const
		 1개의 데이터 저장소
		 ** 데이터형이 없다 : 값을 대입하면 자동으로 인식
		 예)
		 	let a = 10 => a:number
		 	
		 	
		var / let
			  --- 블록을 벗어나면 바로 사라진다 (사용범위 명확)
		-- 사용범위가 명확하지않다 => 메모리 누수현상 발생
		const : 상수 => 값을 변경할 수 없다 => final 
		---- 서버에서 값을 받는 경우 
	--------------------------------------------------------------
	단점 => 데이터형 확인이 어렵다 : 가독성이 떨어진다 
			보완 : 타입스크립트 ---- 실행하면 자바스크립트로 변경됨 
				let a:string = "";
	자바스크립트에서 제공하는 데이터형
	1) 기본형 : number, string, boolean, null, undefined, bigint
	2) 참조형 : object, array, function
	=> function을 데이터형으로 취급
	=> 매개변수로 사용 가능
	
	데이터형 확인 : typeof
	형변환 :
		숫자변환 : Number("10") -> 10
				parseInt("10") -> 10
		문자변환 : String(10) => "10"
		논리변환 : Boolean(0) -> false
				Boolean(1) -> true
				0,0.0,null 이 아닌 경우 true
		** 서버 연결 **************************************************
		List : [배열]
		VO : {} (객체)
		
		자바스크립트 --------------------------- 오라클 : 연결하는 기능이 없다 (NodeJS만 가능)
		자바스크립트------------------자바------------------오라클
									| MVC(Spring, Spring-boot)
									
CDN??
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//main => 자동실행되는 함수 -시작함수
	/*
	window.onload=()=>{
		
	}
	*/
	window.onload=function(){
		/*
			프로그램에 필요한 변수 선언
			변수 선언 : var/let/const
			
			출력
			1.console.log() : 개발자도구
			2.document.write(출력) => 브라우저출력
			3. alert() : 팝업
			4. 태그안에 값 주입 : innerHTML
			
		*/
		
		//데이터형 확인: typeof
		let a=10
		let b=10.5
		let c='Hello JavaScript'
		let d="Hello JavaScript"
		let e=[1,2,3,4,5]
		let f={
					name: "홍길동",
					age: 20
			}
		let g=function(){}
		let m
		let k=true
		
		console.log("a:"+ a +", type:"+ typeof a)
		console.log("b:"+ b +", type:"+ typeof b)
		console.log("c:"+ c +", type:"+ typeof c)
		console.log("d:"+ d +", type:"+ typeof d)
		console.log("e:"+ e +", type:"+ typeof e)
		console.log("f:"+ f +", type:"+ typeof f)
		console.log("g:"+ g +", type:"+ typeof g)
		console.log("m:"+ m +", type:"+ typeof m)
		console.log("k:"+ k +", type:"+ typeof k)
		
		//변수값 변경
		let aa = "Hello JavaScript"
		aa="Hello"
		console.log("aa:"+aa)
		
		//var은 변수명이 중복되면 덮어쓰기 됨 => let 권장
		var bb =10
		var bb =20
		console.log("bb:"+bb)
		
		// 단점 => 한개 변수로 데이터형 변경 가능 -> 가독성이 좋지않음(데이터형 파악이 어렵다)
		let cc =10
		cc= 'hello'
		cc= [1,2,3]
		cc= true
		
		//상수 const 는 변경 불가
		/*
		const dd = 10
		dd = 'hello'
		console.log("dd:"+dd)
		*/
	}
</script>
</head>
<body>

</body>
</html>