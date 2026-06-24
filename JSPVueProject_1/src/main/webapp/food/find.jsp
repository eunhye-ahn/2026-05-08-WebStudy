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
  width: 100%;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
.a-link:hover{
	cursor: pointer;
}

</style>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<select class="input-sm" v-model="column">
			<option value="type">음식종류</option>
			<option value="name">업체명</option>
			<option value="address">주소</option>
		</select>
		<input type="text" size="20" class="input-sm" v-model="ss">
		<button type="button" class="btn-sm btn-primary" @click="find()">검색</button>
	</div>
	<div class="row" style="margin-top:20px">
		<div class="col-sm-3" v-for="vo in food_list">
        <a :href="'../food/detail.do?no='+vo.no">
          <div class="thumbnail">
            <img :src="vo.poster" :title="vo.address" style="width:250px;height: 150px;object-fit:cover">
            <p>{{vo.name}}</p>
          </div>
        </a>
      </div>
	</div>
	<div class="row text-center" style="margin-top:20px">
		<ul class="pagination">
	        <li v-if="startPage>1"><a class="a-link" @click="move(startPage-1)">&laquo;</a></li>
	        <li v-for="i in range(startPage,endPage)" :class="i===curpage?'active':''"><a class="a-link" @click="move(i)">{{i}}</a></li>
	        <li v-if="endPage<totalpage"><a class="a-link" @click="move(endPage+1)">&raquo;</a></li>
	      </ul>
	</div>
</div>
<script>
let find = Vue.createApp({
	data(){
		return{
			startPage:0,
			endPage:0,
			totalpage:0,
			curpage:1,
			column:'address',
			ss:'마포',
			food_list:[]
		}
	},
	mounted(){
		this.dataRecv()
	},
	methods:{
		dataRecv(){
			axios.get('../food/find_vue.do',{
				params:{
					page:this.curpage,
					column:this.column,
					ss:this.ss
				}
			}).then(res=>{
				console.log(res.data)
				this.food_list=res.data.food_list
				this.startPage=res.data.startPage
				this.endPage=res.data.endPage
				this.curpage=res.data.curpage
				this.totalpage=res.data.totalpage
			})
		},
		find(){
			this.curpage=1
			axios.get('../food/find_vue.do',{
				params:{
					page:this.curpage,
					column:this.column,
					ss:this.ss
				}
			}).then(res=>{
				console.log(res.data)
				this.food_list=res.data.food_list
				this.startPage=res.data.startPage
				this.endPage=res.data.endPage
				this.curpage=res.data.curpage
				this.totalpage=res.data.totalpage
			})
		},
		move(page){
			axios.get('../food/find_vue.do',{
				params:{
					page:page,
					column:this.column,
					ss:this.ss
				}
			}).then(res=>{
				console.log(res.data)
				this.food_list=res.data.food_list
				this.startPage=res.data.startPage
				this.endPage=res.data.endPage
				this.curpage=res.data.curpage
				this.totalpage=res.data.totalpage
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
	}
}).mount(".container")
</script>
</body>
</html>