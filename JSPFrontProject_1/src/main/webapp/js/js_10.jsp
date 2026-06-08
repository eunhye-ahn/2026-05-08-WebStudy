<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	[] => 서버에서는 ArrayList : JSONArray
			▲json 직렬화   ▼json 역직렬화
	{} => 서버에서는 VO : JSONObject 
	
	객체
	{키:값} => 숫자/문자열
					| "",'' 선택
				|그냥사용
	=> 키는 멤버변수로 사용
	=> 키의 개수가 동일하지 않을 수 있다
	=> 사용용도 => 자바 = 자바스크립트 연동
			JSON은 데이터를 전송시에 주로 사용
			서버 <=> 브라우저 	서버 <=> 모바일
	=> 사용방법 let sawon = {"sabun":1, "name":"홍길동"}
	sawon.sabun
	sawon.name
		---	객체이므로 .으로 접근해야한다
		
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=()=>{
	const sawon = {"sabun":1,"name":"홍길동","job":"사원"}		//객체표현법
	document.write("<h1>자바스크립트 객체 표현법 (JSON)</h1>")
	document.write("사번:"+sawon.sabun+"<p>")
	document.write("이름:"+sawon.name+"<p>")
	document.write("직위:"+sawon.job+"<p>")
	document.write("<hr>")
}
</script>
</head>
<body>

</body>
</html>