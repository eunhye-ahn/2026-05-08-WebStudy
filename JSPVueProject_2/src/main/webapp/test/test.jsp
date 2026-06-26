<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" 
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9e174327e283f4f12d6e459138fee8f5&libraries=places">
    </script>
</head>
<body>

<input type="text" id="keyword" placeholder="장소 검색">
<button onclick="searchPlaces()">검색</button>

<div id="map" style="width:100%;height:400px;"></div>
<ul id="placesList"></ul>

<!-- 선택된 장소 데이터 담을 hidden 필드 -->
<input type="hidden" id="selectedPlaceName" name="placeName">
<input type="hidden" id="selectedLat" name="lat">
<input type="hidden" id="selectedLng" name="lng">
<input type="hidden" id="selectedAddress" name="address">

<script>
    var map = new kakao.maps.Map(document.getElementById('map'), {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 3
    });

    var ps = new kakao.maps.services.Places();
    var markers = [];

    function searchPlaces() {
        var keyword = document.getElementById('keyword').value;
        if (!keyword.trim()) return alert('키워드를 입력하세요!');

        // ✅ 핵심: 콜백으로 결과 받아옴
        ps.keywordSearch(keyword, placesSearchCB);
    }

    function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
            displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert('검색 결과가 없습니다.');
        } else {
            alert('검색 중 오류가 발생했습니다.');
        }
    }

    function displayPlaces(places) {
        var listEl = document.getElementById('placesList');
        listEl.innerHTML = '';
        removeMarkers();

        places.forEach(function(place, index) {
            // 마커 생성
            var marker = addMarker(new kakao.maps.LatLng(place.y, place.x));

            // 목록 아이템 생성
            var li = document.createElement('li');
            li.innerHTML = place.place_name + ' - ' + place.road_address_name;
            li.style.cursor = 'pointer';

            // ✅ 클릭 시 장소 데이터 선택
            li.onclick = function() { selectPlace(place); };
            kakao.maps.event.addListener(marker, 'click', function() { selectPlace(place); });

            listEl.appendChild(li);
        });
    }

    // ✅ 장소 선택 시 데이터 저장
    function selectPlace(place) {
        console.log('선택된 장소:', place);  // 전체 데이터 확인

        document.getElementById('selectedPlaceName').value = place.place_name;
        document.getElementById('selectedLat').value = place.y;   // 위도
        document.getElementById('selectedLng').value = place.x;   // 경도
        document.getElementById('selectedAddress').value = place.road_address_name || place.address_name;

        // 지도 중심 이동
        map.setCenter(new kakao.maps.LatLng(place.y, place.x));

        // 이후 처리: 폼 제출 or Ajax 전송 등
    }

    function addMarker(position) {
        var marker = new kakao.maps.Marker({ position: position });
        marker.setMap(map);
        markers.push(marker);
        return marker;
    }

    function removeMarkers() {
        markers.forEach(function(m) { m.setMap(null); });
        markers = [];
    }
</script>
</body>
</html>