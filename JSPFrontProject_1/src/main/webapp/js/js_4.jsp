<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	논리연산자 : && ||
	&& => 직렬연산자 => 두개의 조건이 트루일 경우일때 트루
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
window.onload=function(){
	let i = (6<7) && (6==7)
	console.log(i)
	let j = (6<7) || (6==7)
	console.log(j)
	
	/*
	대입연산자
	+=, -=. =
	*/
	let k = 10
	k+=10
	console.log(k)
	k-=10
	console.log(k)
	
	let g=(6%2==0 ? "짝수":"홀수")
	console.log(g)
	
	let h=10/0
	console.log(h) 
	//Infinity :0으로 나눈 경우
	//NAN : 연산처리 오류 (문자열을 넘버로 형변환한다거나..)
	/*
		1. 연산처리가 불가능 : null, 값이 없는 경우
					=> 서버 / 입력값
					= NAN (산술연산) => 장바구니 총액
		2. Infinity : 0으로 나눈 경우
		3. 변수의 초기값이 없는 경우 : undefined
		4. 정수/정수 => 실수
		5. HTML에 입력값을 가지고 오는 경우 : string => Number(). parseInt()
		6. 단점 : 서버를 연결할 수 없다 => 서버를 연결할 수 잇는 라이브러리
										=> ajax /axios
		7. 같다 / 같지않다
			==		!=
	*/	
}
</script>
</head>
<body>

</body>
</html>