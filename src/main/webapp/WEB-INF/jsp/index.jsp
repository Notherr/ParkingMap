<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Kakao 지도 시작하기</title>
</head>
<body>
<center>
<h1>kakao map</h1>
    <!-- 지도를 담을 div -->
<div id="map" style="width:500px;height:400px;"></div>
</center>
<!-- kakao 지도 API 가져오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bf58f6204f96c8bac33bd7aaeb780397"></script>
<script>
    <!-- 지도를 담을 div -->
    var container = document.getElementById('map');
    var options = {
        //객체 생성시에 new를 사용.
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도 초기 중심좌표
        level: 3 // 지도 확대 레벨
    };
    <!-- 지도를 생성 / 옵션과 지도를 담을 div로 -->
    var map = new kakao.maps.Map(container, options);

    //HTML5 geoloaction 사용
    if (navigator.geolocation) {

        //Geoloaction 이용해서 접속 위치 얻어오기.
        navigator.geolocation.getCurrentPosition(function(position){

            var lat = position.coords.latitude, //위도
                lon = position.coords.longitude; //경도

            var locPosition = new kakao.maps.LatLng(lat, lon),
                message = '<div style="padding:5px;">내 위치~ </div>';

            displayMarker(locPosition, message);
        });
    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
            message = 'geolocation을 사용할수 없어요..'

        displayMarker(locPosition, message);
    }

    // 지도에 마커와 인포윈도우를 표시하는 함수입니다
    function displayMarker(locPosition, message) {

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: locPosition
        });

        var iwContent = message, // 인포윈도우에 표시할 내용
            iwRemoveable = true;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent,
            removable : iwRemoveable
        });

        // 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);

        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);
    }
</script>
</body>
</html>