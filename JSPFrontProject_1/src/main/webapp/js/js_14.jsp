<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	함수 응용 : 영화진흥원 => JSON으로 전송
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <style>
 .conatiner{
 	margin-top: 50px;
 }
 .row{
 	margin: 0 auto;
 	widht: 960px;
 }
 </style>
 <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
 <script type="text/javascript">
 //변수선언 => 전역변수
 let movie = []
 window.onload=function(){
	 movieList(1)
	/*
	searchMainDailyBoxOffice.do
	searchMainRealTicket.do
	searchMainDailySeatTicket.do
	https://www.kobis.or.kr/kobis/business/main/
	*/
 }
 function movieList(no)
 {
	 //서버연결 movie_list.do?no=1
	 axios.get('movie_list.do',{
		 params: {
			 no:no
		 }
	 //response => out.write(json);
	 }).then(response=>{
		 //response => JSON / 일반문자열
		movie=response.data
		console.log(movie)
		//tobody로 데이터 전송
		let html = '';
		movie.forEach(m=>{
			html+=
				'<tr onmouseover="movieDetail('+m.rank+')">'
				+'<td class="text-center">'+m.rank+'</td>'
				+'<td class="text-center"><img src="https://www.kobis.or.kr'+m.thumbUrl+'" width=30 height=30></td>'
				+'<td class="text-center">'+m.movieNm+'</td>'
				+'<td class="text-center">'+m.director+'</td>'
				+'<td class="text-center">'+m.genre+'</td>'
			+'</tr>'
		})
		document.querySelector('#list tbody').innerHTML=html
	 })
 }
 //const movieDetail = function(mno){}
 const movieDetail=(mno)=>
 {
	 let table = document.querySelector('#detail');
	 table.style.display=''
	 //for문으로 찾지않고 find로 찾기
	 let m = movie.find(m=>m.rank == mno)
	 console.log(m)
	 let img = document.querySelector('#poster');
	 img.src="https://www.kobis.or.kr"+m.thumbUrl
	 document.querySelector('#name').innerHTML=m.movieNm
	 //$('#name').text(m.movieNm) <--------jquery
	 let director = document.querySelector('#director')
	 director.textContent=m.director
	 document.querySelector('#genre').innerHTML=m.genre
	 let grade = document.querySelector('#grade')
	 grade.textContent=m.watchGradeNm
	 document.querySelector('#story').innerHTML=m.synop
	 
 }
 /*
 
 <바닐라JS : 순수하게 자바스크립트만 이용해서 처리>
 1. 함수호출
 	
 2. 이벤트처리
 언제호출하는지
	=onclick : button img
	=onmouseover/onmouseout 
	=onchange
	=onkeydown / onkeyup
 3. 태그읽기 => DOM
 	document.querySelector('CSS선택자')
 	document.getElementById('id명') => id
 	document.getElementByClassName('class명') => class
 	document.getElementByTagName('태그명') => tagname
 	
 */
 
 </script>
</head>
<body>
	<div class="conatiner">
		<div class="row text-center">
			<button class="btn-lg btn-warning" onclick="movieList(1)">박스오피스</button>
			<button class="btn-lg btn-info" onclick="movieList(2)">실시간 예매율</button>
			<button class="btn-lg btn-success" onclick="movieList(3)">좌석 점유율</button>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-sm-8">
				<table class="table" id="list">
					<thead>
						<tr class="success">
							<th class="text-center">순위</th>
							<th class="text-center"></th>
							<th class="text-center">영화명</th>
							<th class="text-center">감독</th>
							<th class="text-center">장르</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			<div class="col-sm-4">
				<table class="table" id="detail" style="display:none">
					<tbody>
						<tr>
							<td colspan="2" class="text-center">
								<img src="" style="width:360px;height:250px;object-fit:cover" id="poster">
							</td>
						</tr>
						<tr>
							<td width=25%>영화명</td>
							<td width=75% id="name"></td>
						</tr>
						<tr>
							<td width=25%>감독</td>
							<td width=75% id="director"></td>
						</tr>
						<tr>
							<td width=25%>장르</td>
							<td width=75% id="genre"></td>
						</tr>
						<tr>
							<td width=25%>등급</td>
							<td width=75% id="grade"></td>
						</tr>
						<tr>
							<td colspan="2" id="story"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>