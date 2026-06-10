<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트 => 기본문법
	| 태그선택 (브라우저 안에서 태그를 제어/추가/삭제/데이터 수정)
			-------------------------------------------동적 (페이지 변경없이 그 자리에서 변경)
	
		JSP ========== 서버
						|
					   JSP : 새로운 JSP (new)
		자바스크립트
		
	언제 제어하는지 => 이벤트
	-------------------
	고전적 이벤트/ 일반 이벤트/ Listener()
	//고전
	btn.onclick(function({})
	//인라인
	<button onclick=""> : vue/react
	//이벤트리스너 등록
	btn.addEventListener('click',function(){}) : jquery
 --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <style class="text/css">
 .container{
 	margin-top: 50px;
 }
 .row{
 	margin: 0 auto;
 	width: 600px;
 }
 h1{
 	text-align: center;
 }
 </style>
 <script type="text/javascript">
 	function gasan(){
 		let kor = document.getElementById("kor")
 		if(kor.value === ""){
 			kor.focus()
 			return
 		}
 		let math = document.getElementById("math")
 		if(math.value === ""){
 			math.focus()
 			return
 		}
 		let eng = document.getElementById("eng")
 		if(eng.value === ""){
 			eng.focus()
 			return
 		}
 		
 		let total = document.getElementById("total")
 		let hap = Number(kor.value)+Number(eng.value)+parseInt(math.value)
 		//웹데이터는 전부 string => 숫자형으로 변환필요 : Number, parseInt
 		total.value = hap
 		
 		let av=hap/3
 		let avg=document.getElementById("avg")
 		avg.value=Math.round(av)
 		
 		let sc = 'A'
 		if(av>=90){
 			sc = 'A'
 		}
 		else if(av>=80){
 			sc = 'B'
 		}
 		else if(av>=70){
 			sc = 'C'
 		}
 		else if(av<70){
 			sc = 'F'
 		}
 		
 		let score = document.getElementById("score")
 		score.value=sc
 	}
 
 	window.onload=()=>{
 		let h2 = document.querySelectorAll("h2")

 		for(let i of h2){
 			i.style.backgroundColor="green"
 			i.textContent="hello javascript"
 		}
 		
 		let acc=document.querySelector("#account")
 		acc.addEventListener('change',function(){
 			let val = acc.value
 			let price = document.querySelector("#price")
 			let total = Number(val)*Number(price.textContent)
 			let sum = document.querySelector("#sum").innerHTML=
 				'<font color="red">'+total+'원</font>'
 		})
 	}
 </script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>성적계산</h1>
			<table class="table">
				<tr>
					<th width=35% class="text-center">국어</th>
					<td width=65%>
						<input type="text" class="input-sm" id="kor" size=15>
					</td>
				</tr>
				<tr>
					<th width=35% class="text-center">수학</th>
					<td width=65%>
						<input type="text" class="input-sm" id="math" size=15>
					</td>
				</tr>
				<tr>
					<th width=35% class="text-center">수학</th>
					<td width=65%>
						<input type="text" class="input-sm" id="eng" size=15>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="button" id="btn" onclick="gasan()"
						class="btn-sm btn-primary" value="계산">
						
					</td>
				</tr>
			</table>
			<h1>처리결과</h1>
			<table class="table">
				<tr>
					<th width=35% class="text-center">총점</th>
					<td width=65%>
						<input type="text" class="input-sm" id="total" size=15 readonly>
					</td>
				</tr>
				<tr>
					<th width=35% class="text-center">평균</th>
					<td width=65%>
						<input type="text" class="input-sm" id="avg" size=15 readonly>
					</td>
				</tr>
				<tr>
					<th width=35% class="text-center">학점</th>
					<td width=65%>
						<input type="text" class="input-sm" id="score" size=15 readonly>
					</td>
				</tr>
			</table>
		</div>
		<div class="row" style="margin-top:20px">
			<h2>Hello DOM -1</h2>
			<h2>Hello DOM -2</h2>
			<h2>Hello DOM -3</h2>
			<h2>Hello DOM -4</h2>
			<h2>Hello DOM -5</h2>
		</div>
		<div class="row" style="margin-top:20px">
			<table class="table">
				<tr>
					<td>
						수량:<select id="account">
							<option value="1">1개</option>
							<option value="2">2개</option>
							<option value="3">3개</option>
							<option value="4">4개</option>
							<option value="5">5개</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
					가격:<span id="price">3000</span>
					</td>
				</tr>
				<tr>
					<td>
					총금액:<span id="sum"></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>