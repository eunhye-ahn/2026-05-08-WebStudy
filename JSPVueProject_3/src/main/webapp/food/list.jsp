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
  width: 960px;
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
<script type="text/javascript" src="../common/pagecard.js"></script>
</head>
<body>
<div class="container" id="listApp">
	<div class="row">
			<div class="col-sm-3" v-for="(item,index) in food_list" :key="index">
				<a :href="'../food/detail.do?no='+item.no">
					<div class="thumbnail">
						<img :src="item.poster" style="width:250px;height:130px;object-fit:cover" >
						<p>{{item.name}}</p>
					</div>
				</a>
			</div>
	</div>
	<div class="row text-center" style="margin-top:20px">
	<%-- :curPage="curpage" => 오류발생확률 높음 (지양) :cur-page="curpage" (권장)  --%>
		<pagecard 
		 :cur-page="curpage"
		 :end-page="endPage"
		 :start-page="startPage"
		 :total-page="totalpage"
		 @page-change="move"
		></pagecard>
	</div>
</div>
<script>
let list=Vue.createApp({
	data(){
		return{
			goods_list:[],
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			arr:[]
		}	
	},
	mounted(){
		this.dataRecv()
	},
	methods:{
		dataRecv(){
			axios.get("../food/list_vue.do",{
				params:{
					page:this.curpage
				}
			}).then(res=>{
				//console.log(res)
				this.food_list=res.data.food_list
				this.curpage=res.data.curpage
				this.totalpage=res.data.totalpage
				this.endPage=res.data.endPage
				this.startPage=res.data.startPage
			})
		},
		move(page){
			this.curpage=page
			this.dataRecv()
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
		  }
	},
	components:{
		pagecard:pagecard
	}
	
}).mount("#listApp")
</script>
</body>
</html>