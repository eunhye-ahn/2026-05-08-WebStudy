const replycard={
	props:{
		no:Number,
		cno:Number,
		replyList:[],
		msg:'',
		uMsg:'',
		loginId:''
	},
	methods:{
		
	},
	template:
	`
	<div class="pannel pannel-success" style="">
			<div class="panel-body">
				<div class="row">
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
					<table class="table" v-if="loginId">
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
	`
}