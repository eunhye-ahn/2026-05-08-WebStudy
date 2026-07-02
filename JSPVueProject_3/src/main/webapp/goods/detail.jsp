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
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
<div class="container" id="detailApp">
	<div class="panel panel-success product-card">
		<div class="panel-body">
			<div class="row">
				<div class="col-sm-4 text-center">
					<img :src="vo.goods_poster" class="img-response img-thumbnail" style="width:100%;max-height:350px">
					
				</div>
				<div class="col-sm-8">
					<h3>{{vo.goods_name}}</h3>
					<p class="text-muted">
						{{vo.goods_sub}}
					</p>
					<hr>
					<table class="table">
						<tr>
							<th width="15%">가격</th>
							<td width="85%">{{vo.goods_price}}</td>
						</tr>
						<tr>
							<th width="15%">특가</th>
							<td width="85%">{{vo.goods_discount}}%</td>
						</tr>
						<tr>
							<th width="15%">배송</th>
							<td width="85%">{{vo.goods_delivery}}</td>
						</tr>
					</table>
					<div class="form-inline">
						<button class="btn btn-primary">장바구니</button>
						<button class="btn btn-danger" @click="buyBtn()">바로구매</button>
						<button class="btn btn-success" @click="go()">목록</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="pannel pannel-success" style="">
		<div class="panel-body">
			<script src="../common/replycomponent.js"></script>
			<replycomponent :cno="cno" :rno="no" :login-id="loginId"></replycomponent>
		</div>
	</div>
</div>
<script>
var IMP = window.IMP; 
IMP.init(""); 

let detail = Vue.createApp({
	data(){
		return{
			no:${param.no},
			vo:{},
			cno:${cno},
			replyList:[],
			msg:'',
			uMsg:'',
			loginId:'${sessionScope.id}'
		}
	},
	mounted(){
		this.detail()
		axios.get('../reply/list_vue.do',{
			params:{
				cno:this.cno,
				rno:this.no
			}
		}).then(res=>{
			console.log(res.data)
			this.replyList=res.data
		})
	},
	methods:{
		detail(){
			axios.get("../goods/detail.do",{
				params:{
					no:this.no
				}
			}).then(res=>{
				console.log(res.data)
				this.vo=res.data
			})
		},
		/*
				BOM / DOM
				|
				window : 브라우저 = 외곽담당(주소창,메뉴,상태바)
					=window.open : 팝업창
					=window.close
					=window.document : html화면위치
					=window.location.href: 화민이동
					=window.history.back() / window.history.forward()
					=window.screen
		*/
		go(){
			window.history.back()
		},
		buyBtn(){
			this.requestPay(this.vo.goods_name,this.vo.price)
		},
		requestPay(name,price) {
		    IMP.request_pay({
		        pg: "html5_inicis",
		        pay_method: "card",
		        merchant_uid: "ORD20180131-0000011",   // 주문번호
		        name:  name,
		        amount: price,         // 숫자 타입
		        buyer_email: '',
		        buyer_name: '',
		        buyer_tel:'',
		        buyer_addr: '',
		        buyer_postcode: ''
		    }, function (rsp) { // callback
		    	
		    	alert("구매가 완료되었습니다.\n마이페이지에서 확인하세요")
		    	//window.location.href="../mypage/buy_list.do"
		   });
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
	},
	components:{
		replycomponent:ReplyComponent
	}
}).mount("#detailApp")
</script>
</body>
</html>