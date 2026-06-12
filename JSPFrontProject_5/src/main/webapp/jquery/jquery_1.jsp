<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	Jquery : DOM (태그를 제어하는 라이브러리)
				  -------- 
				  1.태그선택
				  	$(CSS선택자)
				  	----------
				  	1) id			=>#id
				  	2) class		=>.class
				  	3) tag			=> tag
				  	4) 속성선택자		=> tag[속성=값]
				  					   tag[속성*=값]
				  	5) 자손/후손 선택자		=> tag > tag
				  						=> tag tag
				  	6) 구조선택자 			=> tag:eq(0) / tag:nth-child(1)
				  2.변경		
				  	1) 속성
				  		$(태그).attr('속성명','값')
				  	2) 값
				  		input, textarea, select
				  		$(태그).val() : 값읽기
				  		$(태그).val('') : 값변경
				  		---------
				  		$(태그).text('') : 값읽기
				  		$(태그).text('') : 값변경
				  	3) html 추가
				  		$(태그).html() => html읽기
				  		$(태그).html('') => html변경
				  	4) 여러개 추가
				  		위에 출력 : $(태그).prepend('')
				  		아래에 출력 : $(태그).append('')
				  	5) 스타일
				  		$(태그).css('속성명','값')
				  			:margin-top -> marginTop
				  3.이벤트
				  	$(태그).click(function(){}) => 고전적인 이벤트
				  		   -----
				  		   keydown/keyup
				  		   mousedown/mouseup
				  		   mouseout/moseover : hover
				  		   change
				  	$(태그).on('click',function(){}) => 리스너 등록
				  	=> checkbox 처리
				  4.서버연결
				  5.효과(약간)/애니메이션 => JqueryUI / plugIN => CDN 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
//$(document).ready(fuction(){}) => onload
/**
 * prop => property
 	: checkbox / radio 에서 쓰임
 */
$(function(){		
	$('#btn1').on('click',function(){
		let len = $('input[type=checkbox]:checked').length
		alert("선택된 취미 :"+len+"입니다")
	})
	//전체동의
	$('#btn2').on('click',function(){
		$('input[type=checkbox]').prop('checked',true)
	})
	$('#btn3').on('click',function(){
		$('input[type=checkbox]').prop('checked',false)
	})
})
</script>
</head>
<body>
	<button id="btn1">확인</button>
	<button id="btn2">전체</button>
	<button id="btn3">해제</button>
	<p>
	<input type="checkbox" checked>낚시
	<input type="checkbox" checked>운동
	<input type="checkbox" checked>쇼핑
	<input type="checkbox" checked>등산
	<input type="checkbox" checked>게임
</body>
</html>