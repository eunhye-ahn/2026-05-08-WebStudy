<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%--
  	태그조작 : 태그를 가져오는방법
  		dcoument.querySelector = >$()
  		문자변경
  			textContent		=>text()
  			innerHTML		=>html()
  		스타일변경
  			태그.style.속성	=>css()
  		속성변경
  			img.src=""		=>attr()
  			a.href=""
  	=> vue/react => 함수가 없다 => 바닐라JS를 사용해서 변경
  			| ref
  		| v-model : 양방향통신
  	
  	이벤트
  		: 사용자가 행위를 한 경우 => 브라우저 안에서
  				---- 키보드/마우스
  				onclick onmouseover onmouseout
  				onkeydown onkeyup
  				onchange onsubmit
   --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function btnClick(){
	alert("버튼클릭")
}
</script>
</head>
<body>
<button onclick="btnClick()">클릭</button>
</body>
</html>