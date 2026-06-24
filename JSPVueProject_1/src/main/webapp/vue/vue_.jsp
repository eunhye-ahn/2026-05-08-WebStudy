<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width: 800px;
}
h1 {
   text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript">
$(function(){
	$('#t').on('keyup',function(){
		let msg = $(this).val()
		$('#print').text(msg)
	})
})
</script>
</head>
<body>
<div class="container">
	<div class="row">
		<input type="text" size="20" id="t">
		<div id="print"></div>
	</div>
	<div class="row" id="app">
		<input type="text" size="20" style="background-color:yellow" v-model="msg">
		<div>{{msg}}</div>
	</div>
</div>
<script>
let vtest=Vue.createApp({
	//관리할 데이터
	data(){
		return{
			msg:''
		}
	},
	mounted(){
		this.msg = '테스트'
	}
}).mount('#app')
</script>
</body>
</html>