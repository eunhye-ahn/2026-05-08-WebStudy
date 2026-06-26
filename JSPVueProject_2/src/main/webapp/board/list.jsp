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
</style>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<table class="table">
			<tr>
				<td>
					<a href="../board/insert.do" class= "btn btn-sm btn-warning">새글</a>
				</td>
			</tr>
		</table>
		<h3>자료실</h3>
		<table class="table">
			<tr class="success">
				<th class="text-center" width=10%>번호</th>
				<th class="text-center" width=45%>제목</th>
				<th class="text-center" width=15%>이름</th>
				<th class="text-center" width=20%>작성일</th>
				<th class="text-center" width=10%>조회수</th>
			</tr>
			
				<tr v-for="vo in board_list">
					<td class="text-center" width=10%>{{vo.no}}</td>
					<td width=45%><a :href="'detail.jsp?no='+vo.no">{{vo.subject}}</a></td>
					<td class="text-center" width=15%>{{vo.name}}</td>
					<td class="text-center" width=20%>{{vo.dbday}}</td>
					<td class="text-center" width=10%>{{vo.hit}}</td>
				</tr>
		</table>
		<table class="table">
			<tr>
				<td class="text-center">
					<ul class="pagination">
						<li v-if="curpage>1"><a @click="move(curpage-1)">이전</a></li>
						<p>{{curpage}} page / {{totalpage}} pages</p>
						<li v-if="curpage<totalpage"><a @click="move(curpage+1)">다음</a></li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
let list = Vue.createApp({
	data(){
		return{
			curpage:1,
			board_list:[],
			totalpage:0,
			endPage:0,
			startPage:0
		}
	},
	mounted(){
		this.dataRecv()
	},
	methods:{
		dataRecv(){
			axios.get('../board/list.do',{
				params:{
					page:this.curpage
				}
			}).then(res=>{
				console.log(res.data)
				this.board_list=res.data.board_list
				this.curpage=res.data.curpage
				this.totalpage=res.data.totalpage
				this.startPage=res.data.startPage
				this.endPage=res.data.endPage
			})
		},
		range(start,end){
			  let arr=[]
			  let len=end-start
			  for(let i=0;i<=len;i++)
			  {
				  arr[i]=start
				  start++
			  }
			  return arr
		  },
		  move(page){
			  axios.get('../board/list.do',{
					params:{
						page:page
						
					}
				}).then(res=>{
					console.log(res.data)
					this.board_list=res.data.board_list
					this.curpage=res.data.curpage
					this.totalpage=res.data.totalpage
					this.startPage=res.data.startPage
					this.endPage=res.data.endPage
				})
		  }
	}
}).mount(".container")
</script>
</body>
</html>