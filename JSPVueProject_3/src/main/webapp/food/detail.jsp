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
.btn{
	margin-left: 5px
}
</style>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="container" id="detailApp">
	<div class="panel panel-success product-card">
		<div class="panel-body">
			<div class="row">
						<table class="table">
					<tbody>
						<tr>
							<th width="30%" class="text-center" rowspan="8">
								<img :src="vo.poster" style="width:100%;object-fit:cover">
							</th>
							<td colspan="2">
								<h3>{{vo.name}}</h3>
							</td>
						</tr>
						<tr>
							<th width="10%" class="text-center">주소</th>
							<td width="60%">{{vo.address}}</td>
						</tr>
						<tr>
							<th width="10%" class="text-center">전화</th>
							<td width="60%">{{vo.phone}}</td>
						</tr>
						<tr>
							<th width="10%" class="text-center">음식종류</th>
							<td width="60%">{{vo.type}}</td>
						</tr>
						<tr>
							<th width="10%" class="text-center">주차</th>
							<td width="60%">{{vo.parking}}</td>
						</tr>
						<tr>
							<th width="10%" class="text-center">가격대</th>
							<td width="60%">{{vo.price}}</td>
						</tr>
						<tr>
							<th width="10%" class="text-center">영업시간</th>
							<td width="60%">{{vo.time}}</td>
						</tr>
						<tr>
							<th width="10%" class="text-center">테마</th>
							<td width="60%">{{vo.theme}}</td>
						</tr>
						<tr>
							<td colspan="3">
								{{vo.content}}
							</td>
						</tr>
						<tr>
							<td colspan="3" class="text-right">
								<button type="button" class="btn-xs btn-danger" v-if="loginId">좋아요</button>
								<button type="button" class="btn-xs btn-success" v-if="loginId">찜하기</button>
								<button type="button" class="btn-xs btn-info" v-if="loginId">예약하기</button>
								<button type="button" class="btn-xs btn-primary" @click="go()">목록</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="pannel pannel-success" style="">
		<div class="panel-body">
			<table class="table" v-if="replyList.length===0">
				<tr>
					<td class="text-center">
						<strong>댓글이없습니다</strong>
					</td>
				</tr>
			</table>
			<table class="table" v-else>
					<tr>
						<td>
							<table class="table"  v-for="rvo in replyList" :key="rvo.no">
								<tr>
									<td class="text-left" width="80%">●{{rvo.name}} {{rvo.dbday}}</td>
									<td class="text-right" width="20%">
										<button class="btn-xs btn-success" v-if="rvo.id===loginId" @click="toggle(rvo)">{{rvo.show?"취소":"수정"}}</button>
										<button class="btn-xs btn-info" v-if="rvo.id===loginId" @click="deleteReply(rvo.no)">삭제</button>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="white-space:pre-wrap;">
										{{rvo.msg}}
									</td>
								</tr>
								
								<tr>
									<td colspan="2" v-show="rvo.show">
										<textarea rows="4" cols="60" style="float:left" v-model="rvo.umsg"></textarea>
										<input type="button" value="댓글수정" class="btn btn-lg btn-primary" style="width:100px;height:88px;float:left;margin-left:3px" @click="update(rvo)">
									</td>
								</tr>
								
							</table>
						</td>
					</tr>
				</table>
				<table class="table" v-if="loginId" style="margin:0px auto">
					<tr>
						<td colspan="2">
							<textarea rows="4" cols="60" style="float:left" v-model="msg"></textarea>
							<input type="button" value="댓글쓰기" class="btn btn-lg btn-primary" style="width:100px;height:88px;float:left;margin-left:3px" @click="insert()">
						</td>
					</tr>
				</table>
		</div>
	</div>
</div>
<script>
let app = Vue.createApp({
	data(){
		return{
			no:${no},
			cno:${cno},
			vo:{},
			loginId:'${sessionScope.id}',
			replyList:[],
			msg:''
		}
	},
	mounted(){
		this.dataRecv()
		axios.get('../reply/list_vue.do',{
			params:{
				cno:this.cno,
				rno:this.no
			}
		}).then(res=>{
			this.replyList=res.data
		})
	},
	methods:{
		async dataRecv(){
			await axios.get("../food/detail_vue.do",{
				params:{
					no:this.no
				}
			}).then(res=>{
				console.log(res)
				this.vo=res.data
			})
		},
		go(){
			window.location.href="../food/list.do"
		},
		insert(){
			axios.get("../reply/insert.do",{
				params:{
					cno:this.cno,
					rno:this.no,
					msg:this.msg
				}
			}).then(res=>{
				console.log(res)
				this.replyList=res.data
				this.msg=""
			})
		},
		deleteReply(no){
			axios.get("../reply/delete.do",{
				params:{
					no:no,
					cno:this.cno,
					rno:this.rno
				}
			}).then(res=>{
				console.log(res)
				this.replyList=res.data
			})
		},
		toggle(rvo){
			this.replyList.forEach(r=>{
				//선택된게 아니면 false 
				if(r.no!=rvo.no){
					r.show=false
				}
			})
			//선택된건 변경
			rvo.show=!rvo.show
		},
		update(rvo){
			axios.post("../reply/update.do",{},{
				params:{
					no:rvo.no,
					cno:rvo.cno,
					rno:rvo.rno,
					msg:rvo.umsg
				}
			}).then(res=>{
				this.replyList=res.data
			})
		}
	}
}).mount("#detailApp")
</script>
</body>
</html>