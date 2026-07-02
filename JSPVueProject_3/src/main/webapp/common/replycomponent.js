const ReplyComponent={
	//부모에게 받는 값
	/**
	 * <reply :rno="no" :cno="cno" login-id="loginLd"></reply>
	 * 
	 * 전에는 이벤트를 등록해서 컴포넌트 이벤트를 부모에서 감지하고 부모가 처리했는데
	 * 
	 * 이번방식은 자식에서 호출하고 출력하는 형식으로 했다
	 * => 가능한 이유 호출값을 출력하는 곳이 자식컴포넌트에 있어서
	 */
	props:['rno','cno','loginId'],
	template:
	`
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
	`,
	//서버통신
	data(){
		return{
			replyList:[],
			msg:''
		}
	},
	mounted(){
		this.detailData()
	},
	methods:{
		detailData(){
			axios.get('../reply/list_vue.do',{
						params:{
							cno:this.cno,
							rno:this.rno
						}
					}).then(res=>{
						console.log(res.data)
						this.replyList=res.data
			})
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
}