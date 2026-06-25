<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style>
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 900px;
}
h3 {
	text-align: center;
}
</style>
</head>
<body>
 <div class="container">
	<div class="row">
		<h3>글쓰기</h3>
		<table class="table">
			<tr>
				<th width=10% class="text-center">이름</th>
				<td width=90%><input v-model="name" type="text" size=20 name=name class="input-sm" required></td>
			</tr>
			<tr>
				<th width=10% class="text-center">제목</th>
				<td width=90%><input v-model="subject" type="text" size=60 name=subject class="input-sm" required></td>
			</tr>
			<tr>
				<th width=10% class="text-center">내용</th>
				<td width=90%>
					<textarea rows="10" v-model="content" cols="61" name=content required></textarea>
				</td>
			</tr>
			<tr>
				<th width=10% class="text-center">첨부파일</th>
				<td width=90%><input type="file" size=30 name=upload class="input-sm"></td>
			</tr>
			<tr>
				<th width=10% class="text-center">비밀번호</th>
				<td width=90%><input v-model="pwd" type="password" size=60 name=pwd class="input-sm" required></td>	
			</tr>
			<tr >
				<td colspan="2" class="text-center">
					<button class="btn-sm btn-primary" @click="insert()">글쓰기</button>
					<button class="btn-sm btn-primary" type="button"
					onclick="javascript:history.back()">취소</button>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
let insert = Vue.createApp({
	data(){
		return{
			name:'',
			subject:'',
			content:'',
			pwd:'',
		}
	},
	mounted(){
		
	},
	methods:{
		insert(){
			axios.get('../board/insert_ok.do',{
				params:{
					name:this.name,
					subject:this.subject,
					content:this.content,
					pwd:this.pwd
				}
			}).then(res=>{
				window.location.href="../board/list.jsp"
			})
		}
	}
}).mount('.container')
</script>
</body>
</html>