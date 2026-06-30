<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div  id="log">
<div class="container text-right" v-if="!logedIn">
		ID:<input type="text" size="15" ref="idRef" class="input-sm">
		&nbsp;
		PWD:<input type="password" size="15" ref="pwdRef" class="input-sm">
		&nbsp;
		<button class="btn-sm btn-danger" @click="login()">로그인</button>
</div>
<div v-else class="container text-right">
	<p>{{name}}님 안녕하세요</p>
	<button class="btn-sm btn-primary" @click="logout()">로그아웃</button>
</div>
</div>
<hr>
<jsp:include page="${main_jsp }"></jsp:include>
<script>
let log = Vue.createApp({
	data(){
		return{
			id:'',
			pwd:'',
			logedIn:'${sessionScope.id}',
			name:'${sessionScope.name}'
		}
	},
	mounted(){
		
	},
	methods:{
		login(){
			axios.get("../member/login.do",{
				params:{
					id:this.$refs.idRef.value,
					pwd:this.$refs.pwdRef.value
				}
			}
			).then(res=>{
				console.log(res)
				if(res.data === "OK"){
					alert("로그인에 성공했습니다")
					this.logedIn=true
				}
				else if(res.data==="NOID"){
					alert("아이디가 틀렸습니다")
					this.$refs.idRef.focus()
				}
				else{
					this.$refs.pwdRef.focus()
					alert("비밀번호가 틀렸습니다")
				}
			})
		},
		logout(){
			axios.get("../member/logout.do").then(
				window.location.href="../main/main.do"
			)
		}
	}
}).mount("#log")
</script>
</body>
</html>