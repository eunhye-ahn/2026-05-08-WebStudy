<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let httpRequest = null
// $ajax({})
function getXMLHttpRequest(){
	// 브라우저 내장 객체 => XMLHttpRequest => 서버 => 처리 결과 읽어오는 역할
	// => 바닐라JS
	if(window.XMLHttpRequest){	//null이 아니면 => 브라우저에 존재
		return new XMLHttpRequest()		
	}
	else{
		return null;
	}
}
// 서버에 전송 => 수신  => $ajax({method:....})
function sendRequest(url, params, callback, method){
	//1.객체생성 : XMLHttpRequest
	httpRequest = getXMLHttpRequest()
	//2.메서드 방식 지정
	let httpMethod = method?method:'GET'
	if(httpMethod!='GET' && httpMethod!='POST'){
		httpMethod = 'GET'
	}
	//3.params처리 => ?id=admin
	let httpParams= (params===null || params==="")? null:params
	//4.url변경
	let httpUrl=url
	//4-1.get방식
	if(httpMethod === 'GET' || httpParams!=null){
		httpUrl = httpUrl+"?"+httpParams
	}
	//4-2.post방식
	//5.서버 연결 => open(내장객체) => true(Async 비동기)
	httpRequest.open(httpMethod,httpUrl,true)
	//6.데이터전송
	//한글처리
	httpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded')
	//7.결곽값을 읽어오는 함수 지정 => 자동호출 => callback
	//success:function(){}
	httpRequest.onreadystatechange=callback
	//8.데이터전송
	httpRequest.send(httpMethod==='POST'?httpParams:null)
	//9.값을 읽은 경우 : responseText / responseXML(JSON)
	
}
function send(){
	sendRequest("sub.jsp",null,ok,"POST")
}
//콜백함수 : 자동호출
function ok(){
	/*
	readyState
	0 : 서버 연결준비
	1 : 서버 연결	open()
	2 : 서버 연결완료
	3 : 데이터 전송준비 send()
	4 : 데이터 전송 완료
	
	200 => 정상수행
	404 405 500 ...
	success: 4 200
	error:
	*/
	if(httpRequest.readyState === 4){
		if(httpRequest.status === 200){
			let div = document.querySelector("#print")
			div.innerHTML = httpRequest.responseText
		}
	}
}
</script>
</head>
<body>
<button onclick="send()">전송</button>
<div id="print"></div>
</body>
</html>