/**
 * 객체
 * : 태그의 속성을 이용해서 값을 받는 방식
 * <pagecard curpage="page">
 * 
 * 변수 연결
 * :cur-page="curpage" => curPage
 * 
 * 이벤트 연결
 * @page-change="move" => @click="$emit('page-change',startPage-1)"
 * 
 * 
 * --------------------------------------------------------------------
 * Parent = list
 *  |
 * Child = pagecard
 * 	Parent => Child
 * 			값을 전송
	 * 			- 데이터가 많은 경우
	 * 			props:{
	 * 			
	 * 			}
	 * 			- 데이터가 한개인 경우
	 * 			props:['변수']
	 * 			
	 * 			함수호출
	 * 			------
	 * 			emits:['함수명']
	 * 			
	 * 			Parent <=====> Child
	 * 				=>camelCase => startPage
	 * 				=> - : 지향
	 * 			<pagecard 
	 * 				:start-page="startPage"
	 * 				@함수명="move"
	 * 			>
	 * 
	 * 흐름
	 * pagecard
	 * 	| $emit('page-card',5)
	 * parent <pagecard @page-change="move">
	 * (move(5))
	 * 	| curpage=5
	 * axios : 처리
	 * 
	 * $emit 사용 이유
	 * 1.Child Component에서는 Parent의 데이터를 직접 변경하면 안된다
	 * 		--------------------------------------------
	 * 		Parent에서 변경
	 * 2. $('page-change',5)
	 * 		=> 페이지를 5페이지로 변경해 달라
	 * 		=> move(5)
	 * 
	 * props는 parent => child로 데이터 전송할 때 사용
	 * $emit child == parent 이벤트 전달하는 경우에 사용
	 * 
	 * 
	 * CDN 방식에서 값연결 정석
		 * 데이터전송 : props
		 * 이벤트전달 : emit
	 * ====================> $parent 지양
 */
const pagecard={
	//변수잡기
	props:{
		curPage:0,
		endPage:0,
		startPage:0,
		totalPage:0
	},
	//이벤트 발생 처리
	emits:['page-change'],				//함수호출 => 데이터 제어 (list.jsp:Parent)
	methods:{
		range(start,end){
			let arr = []
			let len = end-start
			for(let i=0;i<=len;i++){
				arr[i]=start
				start++
			}
			return arr
		}
	},
	template:
		`
		<ul class="pagination">
			<li v-if="startPage>1"><a class="nav-link" @click="$emit('page-change',startPage-1)">&laquo;</a></li>
			<li v-for="i in range(startPage,endPage)" @click="$emit('page-change',i)" :class="i==curPage?'active':''"><a class="nav-link">{{i}}</a></li>
			<li v-if="endPage<totalPage"><a @click="$emit('page-change',endPage+1)" class="nav-link">&raquo;</a></li>
		</ul>
		`
}