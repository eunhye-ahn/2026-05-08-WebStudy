
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
 .container {
   margin-top: 50px;
 }
 .row {
   /* 화면에 출력 */
   margin: 0px auto;
   width:960px;
 }
 p {
     overflow: hidden;
     white-space: nowrap;
     text-overflow: ellipsis;
 }
 pagination {
 	display: inline-block;
 }
</style>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
<div class="container">
<div class="row">
	<input type=text size=20 class="input-sm" v-model="address">
	<button @click="find()">검색</button>
</div>
<div class="col-md-4" v-for="vo in list">
    <div class="thumbnail">

        <img :src="vo.poster" style="width:100%">
              <a href="#">
        <div class="caption">
          <p>{{vo.name}}</p>
          
        </div>
      </a>
    </div>
      </div>
      <div class="pagination">
      	<a href="list.jsp?page=">이전</a>
      	<a href="list.jsp?page=">다음</a>
      </div>
      <script>
      	let findApp=Vue.createApp({
      		data(){
      			return{
      			list:[],
      			curpage:1,
      			totalpage:0,
      			address:'마포'
      			}
      		},
      		mounted(){
      			this.dataRecv();
      		},
      		methods:
      			{
      			find(){
      				this.curpage = 1;
      				this.dataRecv();
      			},
          		dataRecv(){
          			axios.get('find.jsp', {
          				params:{
          					page:this.curpage,
          					address:this.address
          				}
          			}).then(response=>{
          				console.log(response.data)
          				this.list=response.data.list
          				this.curpage=response.data.curpage
          				this.totalpage=response.data.totalpage
          			})
          		}
      		}
      	}).mount('.container')
      </script>
</body>
</html>