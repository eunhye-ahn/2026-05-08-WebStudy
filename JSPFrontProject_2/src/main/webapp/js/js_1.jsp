<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	문서 객체 모델(Document Object Model)
	------------ 태그를 제어하는 프로그램
				-----태그를 가지고 오는 문제 (객체 : 태그)
				태그 => 클래스, 속성 => (멤버변수)
				<a href="" target=""></a>
				class a
				{
					String href, target;
				}
	문서 객체 모델(Document Object Model)
	HTML/XML => ML(Markup languate) => 메모리에 저장(트리 형태)
	<html>
		<head>
		</head>
		<body>
			<div></div>
		</body>
	</html>
	-------------------------------> DOM구조 => 트리형태
	
	HTML => 화면 UI
	DOM => 자바스크립트에서 제어를 할 수 있게 만든 구조
	1) 문서 객체 선택
	2) 문서 객체 조작 : 글자조작/스타일조작/속성조작
	3) 이벤트 : 인라인 이벤트 모델/고전이벤트모델/이벤트 등록
	4) -----------------------------------------라이브러리 => Jquery3/Jquery4
															--------------가볍다(추가/삭제/권장)
	CDN : 라이브러리파일을 외부에서 직접 불러오는 방법	
	<script src="..."></script>
	
	문서 객체 선택
	1.속성중에 id가존재
		<button id="btn">
		let btn =document.getElementById("btn")**
		=> 객체
	2.속성중에 class가 존재
		<button class="btns">
		<button class="btns">
		<button class="btns">
		let btns =document.getElementsByClassName("btns") 
		=> 배열 [] -> for문 처리 필요
	*****3.속성이 없는 경우
		document.querySelector("CSS 선택자")
		btn = document.querySelector("#btn")
		btns = document.querySelector(".btns")
		button = document.querySelector("태그명")
		자손 = document.querySelector("태그명 > 태그명")
		후손 = document.querySelector("태그명 태그명")
		속성선택자 = document.querySelector("태그명[속성=값]")
		속성선택자 = document.querySelector("태그명[속성*=값]")
		속성선택자 = document.querySelector("태그명[속성$=값]")
		속성선택자 = document.querySelector("태그명[속성^=값]")
		구조선택자 = document.querySelector("태그명:nth-child(2n)")
	4.태그선택 = document.getElementByTagName("태그명")
	=> 원하는 태그를 읽어온다
		---------------- 속성값 변경/값추가/HTML추가/CSS변경
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*			JSON
 *서버 =============> 데이터전송 ==========> HTML(X), javascript
 														|  HTML에 값 출력
 */
let sawon = {
		name:"홍길동",
		getName:function(){
			//document.write("이름:"+this.name)
			let h1=document.querySelector("h1")
			h1.style.color="red"
			h1.style.background="yellow"
			h1.textContent=this.name
		},
		setName:function(name){
			this.name=name
		}
}
window.onload=()=>{
	sawon.setName("심청이")
	sawon.getName()
	
}
</script>
</head>
<body>
	<h1></h1>
</body>
</html>









