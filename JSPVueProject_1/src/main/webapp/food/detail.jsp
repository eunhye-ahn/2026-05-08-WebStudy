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
button{
	margin-left:2px
}
</style>
<script type="text/javascript" src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="food_detail.js"></script>
</head>
<%-- 
지도 출력 : vue/react 동일 
댓글/좋아요/찜하기/예약
--%>
<body>
<div class="container">
	<div class="row">
		<table class="table">
			<tbody>
				<tr>
					<th width="30%" class="text-center" rowspan="8">
						<img :src="food_detail.poster" style="width:100%;object-fit:cover">
					</th>
					<td colspan="2">
						<h3>{{food_detail.name}}</h3>
					</td>
				</tr>
				<tr>
					<th width="10%" class="text-center">주소</th>
					<td width="60%">{{food_detail.address}}</td>
				</tr>
				<tr>
					<th width="10%" class="text-center">전화</th>
					<td width="60%">{{food_detail.phone}}</td>
				</tr>
				<tr>
					<th width="10%" class="text-center">음식종류</th>
					<td width="60%">{{food_detail.type}}</td>
				</tr>
				<tr>
					<th width="10%" class="text-center">주차</th>
					<td width="60%">{{food_detail.parking}}</td>
				</tr>
				<tr>
					<th width="10%" class="text-center">가격대</th>
					<td width="60%">{{food_detail.price}}</td>
				</tr>
				<tr>
					<th width="10%" class="text-center">영업시간</th>
					<td width="60%">{{food_detail.time}}</td>
				</tr>
				<tr>
					<th width="10%" class="text-center">테마</th>
					<td width="60%">{{food_detail.theme}}</td>
				</tr>
				<tr>
					<td colspan="3">
						{{food_detail.content}}
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<button type="button" class="btn-xs btn-danger">좋아요</button>
						<button type="button" class="btn-xs btn-success">찜하기</button>
						<button type="button" class="btn-xs btn-info">예약하기</button>
						<button type="button" class="btn-xs btn-primary" onclick="javascript:history.back()">목록</button>
					</td>
				</tr>
			</tbody>
		</table>
		<table class="table">
			<tbody>
				<tr>
					<td class="text-center">
						<div id="map" style="width:100%;height:350px;"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<script>
let detail=Vue.createApp({
	data(){
		return{
			//getParameter => param
			no:${param.no},
			food_detail:{}
		}
	},
	mounted(){
		console.log("no="+this.no)
		axios.get('../food/detail_vue.do',{
			params:{
				no:this.no
			}
		}).then(res=>{
			console.log(res.data)
			this.food_detail=res.data
			if(window.kakao && window.kakao.maps){
				this.initMap()
			}
			else{
				this.addScript()
			}
		})
	},
	methods:{
		addScript(){
			const script = document.createElement("script")
			/*global kakao*/
			script.onload=()=>kakao.maps.load(this.initMap)
			script.src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=9e174327e283f4f12d6e459138fee8f5&libraries=services"
			document.head.appendChild(script)
		},
		initMap(){
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
			//function : 자체 this를 가지고 있다 => 화살표함수로 바꾸기 
			/*
				자체 this 보유
					function  => O
					화살표 함수(allow함수) => X
				this 결정시기
					function  => 호출시
					화살표 함수(allow함수) => 선언시
				다른 메서드 연결 (call/apply/bind)   ???????????
					function  => 가능
					화살표 함수(allow함수) => 불가능	
			*/
			geocoder.addressSearch(this.food_detail.address, (result, status) => {
	
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
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+this.food_detail.name+'</div>'
			        });
			        infowindow.open(map, marker);
	
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});    
		}
	}
}).mount(".container")
</script>
</body>
</html>