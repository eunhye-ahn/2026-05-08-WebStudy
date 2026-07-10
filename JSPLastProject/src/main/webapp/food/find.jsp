<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <section class="archive-area section_padding_80" id="findApp">
        <div class="container" style="width: 1024px">
            <div class="row">
             <div class="col-12">
               
	               <select v-model="column" class="input-sm">
	                <option value="address">주소</option>
	                <option value="type">음식종류</option>
	                <option value="name">업체명</option>
	               </select>
	               <input type="text" size="20" class="input-sm" v-model="fd" @keydown.enter="find()" ref="findRef">
	               <button type=button class="btn-sm btn-primary" @click="find()">검색</button>
                
             </div>
            </div>
            <div class="row">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-post wow fadeInUp" data-wow-delay="1.2s" v-for="(vo,index) in list" >
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                           <a :href="'../food/detail.do?no='+vo.no">
                            <img :src="vo.poster" alt="">
                           </a>
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-date">
                                        <a href="#">{{vo.name}}</a>
                                    </div>
                                    <!-- Post Date -->
                                    <div class="post-date">
                                        
                                    </div>
                                </div>
                                <!-- Post Comment & Share Area -->
                                <div class="post-comment-share-area d-flex">
                                    <!-- Post Favourite -->
                                    <div class="post-favourite">
                                        <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> {{vo.likecount}}</a>
                                    </div>
                                    <!-- Post Comments -->
                                    <div class="post-comments">
                                        <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> {{vo.replycount }}</a>
                                    </div>
                                    <!-- Post Share -->
                                    <div class="post-share">
                                        <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </div>
                            <a :href="'../food/detail.do?no='+vo.no">
                                <h4 class="post-headline">{{vo.name }}</h4>
                            </a>
                        </div>
                    </div>
                </div>
                
                <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                               
                                <li class="page-item" v-if="curpage>1">
                                    <a class="page-link" @click="pageChange(curpage-1)"><i class="fa fa-angle-double-left" aria-hidden="true"></i>이전</a>
                                </li>
                             
                                
                                <li class="page-item" v-if="curpage<totalpage">
                                    <a class="page-link" @click="pageChange(curpage+1)">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>Page {{curpage }} of {{totalpage }} results</p>
                        </div>
                    </div>
                </div>

            </div>
            
        </div>
    </section>
<script>
	let find = Vue.createApp({
		data(){
			return{
				fd:'마포',
				column:'address',
				list:[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0
			}
		},
		mounted(){
			this.dataRecv()
		},
		methods:{
			async dataRecv(){
				await axios.get('../food/find.do',{
					params:{
						page:this.curpage,
						column:this.column,
						fd:this.fd
					}
				}).then(res=>{
					console.log(res)
					this.list=res.data.list
					this.curpage=res.data.curpage
					this.totalpage=res.data.totalpage
					this.startPage=res.data.startPage
					this.endPage=res.data.endPage
				})
			},
			find(){
				if(this.fd.trim()===""){
					this.$refs.findRef.focus()
					return
				}
				this.curpage=1
				this.dataRecv()
			},
			pageChange(page){
				this.curpage=page
				this.dataRecv()
			}
		}
	}).mount('#findApp')
</script>
</body>
</html>