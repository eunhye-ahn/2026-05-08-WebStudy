<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	객체배열 : [{},{},{},...]
	JSP 파일
	-------
		- , . 이용이 불가능
		_ , $ 만 사용가능
		a_b.jsp
		class a_b
		=> 파일명이 클래스명이 되므로 => 자바문법을 따라야함(식별자)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//서버에서 값을 받아서 처리
let sawons=[
	{sabun:1,name:"홍길동",dept:"개발부",job:"대리",pay:3600,loc:"서울"},
	{sabun:2,name:"홍길둘",dept:"영업부",job:"사원",pay:3000,loc:"부산"},
	{sabun:3,name:"홍길삼",dept:"기획부",job:"과장",pay:4000,loc:"순천"},
	{sabun:4,name:"홍길사",dept:"개발부",job:"부장",pay:7000,loc:"서울"},
	{sabun:5,name:"홍길오",dept:"개발부",job:"사원",pay:3000,loc:"서울"}
]
//데이터 출력
const sawonList =()=> {
		document.write("<h3>사원목록</h3>")
		document.write("<ul>")
		sawons.map(sa=>{
			document.write("<li>"+sa.name+"("+sa.loc+")"+"</li>")
		})
		document.write("</ul>")
}
const sawonInsert =()=>{
	//객체를 한번에 전송하기
	sawons.push({sabun:6,name:"홍길육",dept:"영업부",job:"대리",pay:3600,loc:"경기"})
}
const sawonDetail=(sabun)=>{
	document.write("<h3>사원 상세</h3>")
	let sawon = sawons.find(sa=>sa.sabun === sabun)
	document.write("사번:"+sawon.sabun+"<p>")
	document.write("이름:"+sawon.name+"<p>")
	document.write("부서:"+sawon.dept+"<p>")
	document.write("직위:"+sawon.job+"<p>")
	document.write("연봉:"+sawon.pay+"<p>")
	document.write("근무지:"+sawon.loc+"<p>")
}
const sawonDelete=(sabun)=>{
	document.write("<h3>사원 삭제-filter</h3>")
	const result = sawons.filter(sa=>sa.sabun !== sabun)
	document.write("<ul>")
		result.map(sa=>{
			document.write("<li>"+sa.name+"("+sa.loc+")"+"</li>")
		})
		document.write("</ul>")
}
window.onload=()=>{
	//sawonList()
	//sawonInsert()
	sawonList()
	sawonDetail(2)
	sawonDelete(2)
}
</script>
</head>
<body>

</body>
</html>