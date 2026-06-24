<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//?????????????????????????????????????
const obj={
	name:'Hong Gil Dong'
}
//function : 자체적으로 this가 있다
function aaa(){
	console.log(this.name)
}
//화살표함수는 this가 없다
const arrow=()=>{
	console.log(this.name)
}
//이름으로 메서드 호출 call
//자바에서 메서드 호출 invoke
window.onload=function(){
	aaa.call(obj)
	//Hong Gil Dong
	arrow.call(obj)
	//없음
}
</script>
</head>
<body>

</body>
</html>