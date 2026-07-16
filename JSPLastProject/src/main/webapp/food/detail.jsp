<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 추천 영역 */
#recommendArea{
    margin-top:30px;
    display:flex;
    flex-wrap:wrap;
    gap:15px;
    justify-content:space-between;
}

/* 추천 카드 */
.recommend-card{
    width:calc((100% - 60px) / 5);
    max-width:160px;
    border:1px solid #ddd;
    border-radius:10px;
    overflow:hidden;
    background:#fff;
    box-shadow:0 2px 8px rgba(0,0,0,.1);
    transition:0.3s;
    cursor:pointer;
}

/* 마우스 올렸을 때 */
.recommend-card:hover{
    transform:translateY(-5px);
    box-shadow:0 5px 15px rgba(0,0,0,.2);
}

/* 이미지 */
.recommend-card img{
    width:100%;
    height:150px;
    object-fit:cover;
    /* display:block; */
}

/* 음식 이름 */
.recommend-title{
    padding:8px;
    text-align:center;
    font-size:13px;
    font-weight:bold;
    color:#333;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
}
</style>
<link rel="stylesheet" href="../css/comment.css">
<script type="text/javascript">
let c=0
$(function(){
	let bCheck = false
	$('.btns').on('click',function(){
		const no = $(this).attr('data-no')
		$('.forms').hide()
		
		if(bCheck==false){
			
			$('#form'+no).show()
			$(this).val("취소")
			bCheck=true
		}
		else{
			$('#form').hide()
			bCheck=false
		}
	})
	
	$('#likeOnBtn').on('click',function(){
		let fno=$(this).attr("data-fno")
		location.href="../like/like_on.do?fno="+fno
	})
	$('#likeOffBtn').on('click',function(){
		let fno=$(this).attr("data-fno")
		location.href="../like/like_off.do?fno="+fno
	})
	
	$('#reBtn').on('click',function(){
		if(c===0){
			$(this).text("닫기")
			$('#recommandArea').show()
			c=1
			$.ajax({
				method:'post',
				url:'../recommand/recommand.do',
				success:function(res){
					let json = JSON.parse(res)
					console.log(json)
					let html=''
					json.forEach((food)=>{
						html+='<div class="">'
								+'<img src="'+food.poster+'" title="'+food.type+'">'
								+'<div class="">'
								+food.name
								+'</div>'
								+'</div>'
					})
					$('#recommandArea').html(html)
				}
			})
		}else{
			$(this).text("추천")
			$('#recommandArea').hide()
			c=0
		}
	})
})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
                <div class="col-12 col-lg-8">
	                <div class="row no-gutters">
		                <div class="col-10 col-sm-11">
								<!-- Related Post Area -->
								<div class="related-post-area section_padding_50">
									<div class="related-post-slider owl-carousel">
										<c:forTokens items="${vo.images }" delims="," var="img">
					                		<!-- Single Related Post-->
					                		<div class="single-post">  
					                        	<div class="post-thumb">
					                             	<img src="https://www.menupan.com${img}" alt="" width="100px">
					                        	</div>
					                       	</div>
					                     </c:forTokens>
					                 </div>
		             			</div>
		             	</div>
	             	</div>
             	</div>
             	<table class="table">
             		<tr>
             			<td width="30%" class="text-center" rowspan="11">
             				<img src="${vo.poster }" style="width:100%;height:450px;object-fit:cover" class="img-rounded">
             			</td>
             		</tr>
             		<tr>
             			<td colspan="2">
             				<h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
             			</td>
             		</tr>
             		<tr>
             			<th width="10%" style="color:gray">주소</th>
             			<td width="60%">${vo.address }</td>
             		</tr>
             		<tr>
             			<th width="10%" style="color:gray">전화</th>
             			<td width="60%">${vo.phone }</td>
             		</tr>
             		<tr>
             			<th width="10%" style="color:gray">음식종류</th>
             			<td width="60%">${vo.type }</td>
             		</tr>
             		<c:if test="${vo.price!=null }">
	             		<tr>
	             			<th width="10%" style="color:gray">가격대</th>
	             			<td width="60%">${vo.price }</td>
	             		</tr>
             		</c:if>
             		<tr>
             			<th width="10%" style="color:gray">영업시간</th>
             			<td width="60%">${vo.time }</td>
             		</tr>
             		<tr>
             			<th width="10%" style="color:gray">주차</th>
             			<td width="60%">${vo.parking }</td>
             		</tr>
             		<tr>
             			<th width="10%" style="color:gray">예약</th>
             			<td width="60%">${vo.reserve }</td>
             		</tr>
             		<tr>
             			<th width="10%" style="color:gray">테마</th>
             			<td width="60%">${vo.theme }</td>
             		</tr>
             		<tr>
             			<td colspan="3">
             				${vo.content }
             			</td>
             		</tr>
             		<tr>
             			<td colspan="3" class="text-right">
	             			<c:if test="${sessionScope.id!=null }">
	             				<button class="like-btn">
		             				<c:if test="${fCheck=='false' }">
									  	<span class="heart" id="likeOnBtn" data-fno="${vo.no }">♡</span>
									 </c:if>
									 <c:if test="${fCheck=='true' }">
									  	<span class="heart" id="likeOffBtn" data-fno="${vo.no }">♥</span>
									 </c:if>
								 </button>
								 <c:if test="${jCount == 0 }">
	             					<a href="../jjim/jjim_on.do?fno=${vo.no }"  class="btn btn-xs btn-info">찜하기</a>
	             				</c:if>
	             				 <c:if test="${jCount == 1 }">
	             					<span href="../jjim/jjim_off.do?fno=${vo.no }" class="btn btn-xs btn-outline-info">찜완료</span>
	             				</c:if>
	             				<c:if test="${vo.reserve!='불가' }">
		             				<button class="btn-xs btn-success">예약하기</button>
	             				</c:if>
	             			</c:if>
	             			<c:if test="${sessionScope.id!=null }">
	             				<button class="btn-xs btn-primary" id="reBtn">추천하기</button>
	             			</c:if>
             				<button class="btn-xs btn-warning" onclick="location.href='../food/food_main.do'">목록</button>
             			</td>
             		</tr>
             	</table>
             	<div id="recommandArea" style="display:none">
             		
             	</div>
             	
             	<table class="table">
             		<tr>
             			<td>
             			<div id="map" style="width:100%;height:350px;"></div>
             			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9e174327e283f4f12d6e459138fee8f5&libraries=services"></script>
						<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						    mapOption = {
						        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						        level: 3 // 지도의 확대 레벨
						    };  
						
						// 지도를 생성합니다    
						var map = new kakao.maps.Map(mapContainer, mapOption); 
						
						// 주소-좌표 변환 객체를 생성합니다
						var geocoder = new kakao.maps.services.Geocoder();
						
						// 주소로 좌표를 검색합니다
						geocoder.addressSearch('${vo.address}', function(result, status) {
						
						    // 정상적으로 검색이 완료됐으면 
						     if (status === kakao.maps.services.Status.OK) {
						
						        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
						
						        // 결과값으로 받은 위치를 마커로 표시합니다
						        var marker = new kakao.maps.Marker({
						            map: map,
						            position: coords
						        });
						
						        // 인포윈도우로 장소에 대한 설명을 표시합니다
						        var infowindow = new kakao.maps.InfoWindow({
						            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
						        });
						        infowindow.open(map, marker);
						
						        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
						        map.setCenter(coords);
						    } 
						});    
						</script>
             			</td>
             		</tr>
             	</table>
		</div>
	</div>
	<h2>맛집리뷰</h2>
	<c:if test="${rCount==0}">
		<div class="text-center">
			<h3>댓글이 없습니다</h3>
		</div>
	</c:if>
		<ul>
			<c:forEach var="rvo" items="${rList }">
				<li class="review-card">
					<div class="review-header">
						<div class="review-nick">${rvo.name }</div>
						<div class="review-date">${rvo.dbday }</div>
					</div>
					<div class="review-text">${rvo.msg }</div>
					<c:if test="${sessionScope.id ==rvo.id }">
						<div><input type="button" class="btn-xs btn-primary btns" value="수정" data-no=${rvo.no }></div>
						<div><input type="button" class="btn-xs btn-danger" value="삭제" onclick="location.href='../review/delete.do?no=${rvo.no}&fno=${rvo.fno }'"></div>
					</c:if>
					<form class="review-form forms" method="post" action="../review/update.do" id="form${rvo.no }" style="display:none">
						<input type="hidden" name="fno" value="${vo.no }">
						<input type="hidden" name="no" value="${rvo.no }">
						<input type="text" name="msg" placeholder="리뷰 입력" value="${rvo.msg}" required>
						<button type="submit">수정</button>
					</form>
				</li>
			</c:forEach>
		</ul>
	<c:if test="${sessionScope.id != null}">
		<form class="review-form" method="post" action="../review/insert.do">
			<input type="hidden" name="fno" value="${vo.no }">
			<input type="text" name="msg" placeholder="댓글 입력" required>
			<button type="submit">등록</button>
		</form>
	</c:if>
</body>
</html>