<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
a{
	margin: 0px 3px;
}
</style>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>내용보기</h3>
			<table class="table">
				<tr>
					<th width=20% class="danger text-center">번호</th>
					<td width=30% class="text-center">{{board_detail.no}}</td>
					<th width=20% class="danger text-center">작성일</th>
					<td width=30% class="text-center">{{board_detail.dbday}}</td>
				</tr>
				<tr>
					<th width=20% class="danger text-center">이름</th>
					<td width=30% class="text-center">{{board_detail.name}}</td>
					<th width=20% class="danger text-center">조회수</th>
					<td width=30% class="text-center">{{board_detail.hit}}</td>
				</tr>
				<tr>
					<th width=20% class="danger text-center">제목</th>
					<td colspan="3">{{board_detail.subject}}</td>
				</tr>
					<tr>
						<th width=20% class="danger text-center">첨부파일</th>
						<td colspan="3">
						<a href="download.jsp?fn="></a>
						</td>
					</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space: pre-wrap;background: white;border:none">{{board_detail.content}}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a href="" class="btn btn-sm btn-warning">수정</a>
						<span class="btn btn-sm btn-warning" @click="btnClick()">{{isOn?'삭제':'취소'}}</span>
						<a href="../board/list.jsp" class="btn btn-sm btn-warning">목록</a>
					</td>
				</tr>
				<tr v-show="bShow">
					<td colspan="4" class="text-right" valign="top" height="200">
						비밀번호 : <input type="password" size=15 class="input-sm" ref="pwdRef" v-model="pwd">
						<input type="hidden" name="no" value="" >
						<button type="submit" class="btn-sm btn-primary" @click="del()"">삭제</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
<script>
const detail = Vue.createApp({
	data(){
		return{
			no:${param.no},
			board_detail:{},
			bShow:false,
			msg:'삭제',
			isOn:true,
			pwd:''
		}
	},
	mounted(){
		this.dataRecv()
	},
	methods:{
		dataRecv(){
			axios.get('../board/detail.do',{
				params:{
					no:this.no
				}
			}).then(res=>{
				console.log(res.data)
				this.board_detail=res.data.board_detail
			})
		},
		btnClick(){
			this.isOn=!this.isOn
			this.bShow=!this.bShow
		},
		del(){
			if(this.pwd.trim()==""){
				this.$refs.pwdRef.focus()
				return
			}
			//데이터전송
			axios.get('../board/delete_vue.do',{
				params:{
					no:this.no,
					pwd:this.pwd
				}
			}).then(res=>{
				console.log(res)
			})
		}
	}
}).mount(".container")
</script>
</body>
</html>