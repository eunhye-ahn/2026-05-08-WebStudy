<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	제어문
	= 조건문
		1.단일조건문(***)		//	true/false는 숫자가 변환이 가능하다
			if(조건문)
			{	---- 부정연산자/비교연산자/논리연산자
				조건이 true일 경우
				조건이 false일 경우 건너뛴다
			}
		2. 선택조건문(***)
			if(조건문)
			{
				조건이 true
			}else
			{
					
			}
					=> 삼항연산자와 동작방식같음
		3. 다중조건문
			if()
			{
			
			}
			else if()
			{
			
			} 
			else 
			{
			
			}
		4. 선택문
	= 반복문
		1. do-while문
		2. while문
		3. 일반 for문(***)
			= for in
			= for of(**)
			= for each(**)
			= map(**)
	= 반목 제어문
		1. break(**)
		2. continue
		
	4.선택문
	switch(number/문자)
	{
		case 값: 
			처리문장 
			break;
		..
		default:
			처리문장
	}
	= 반복문
		
	axios/ajax => 화면이동을 하지않고 서버에서 데이터를 받아오는 기술
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
//blocked by CORS : 브라우저 - 외부서버 통신 불가 
//=> 서버와 서버끼리만 통신가능하므로
//외부서버에서 데이터를 가져오려면 우리 서버를 거쳐서 브라우저로 가져와야한다*******
window.onload=function(){
	let i =3;
	switch(i){
	case 1:
		document.write("<h1>문장1</h1>")
	case 2:
		document.write("<h1>문장2</h1>")
	case 3:
		document.write("<h1>문장3</h1>")
	case 4:
		document.write("<h1>문장4</h1>")
		break;
	default:
		document.write("<h1>default</h1>")
	}
}
//리턴형 X, 매개변수는 변수명만 사용
/**
 * 
searchMainDailyBoxOffice.do
searchMainRealTicket.do
searchMainDailySeatTicket.do
 */
function movieList(no)
{
	let site = "https://www.kobis.or.kr/kobis/business/main/"
	if(no === 1){
		site += "searchMainDailyBoxOffice.do"
	}
	if(no === 2){
		site += "searchMainRealTicket.do"
	}
	if(no === 3){
		site += "searchMainDailySeatTicket.do"
	}
	
	axios.get(site).then(response=>{
		console.log(response.data)
	})
}
</script>
</head>
<body>
</body>
</html>