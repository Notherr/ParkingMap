<!DOCTYPE html>
<!-- jsp에서 한글 문자 인식 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>어따하지</title>
    <style>
        html, body {width:100%;height:100%;margin:0;padding:0;}
        .map_wrap {position:relative;overflow:hidden;width:100%;height:100vh;}
        .radius_border{border:1px solid #919191;border-radius:5px;}
        .custom_typecontrol {position:absolute;top:10px;right:10px;overflow:hidden;width:130px;height:30px;margin:0;padding:0;z-index:1;font-size:12px;font-family:'Malgun Gothic', '맑은 고딕', sans-serif;}
        .custom_typecontrol span {display:block;width:65px;height:30px;float:left;text-align:center;line-height:30px;cursor:pointer;}
        .custom_typecontrol .btn {background:#fff;background:linear-gradient(#fff,  #e6e6e6);}
        .custom_typecontrol .btn:hover {background:#f5f5f5;background:linear-gradient(#f5f5f5,#e3e3e3);}
        .custom_typecontrol .btn:active {background:#e6e6e6;background:linear-gradient(#e6e6e6, #fff);}
        .custom_typecontrol .selected_btn {color:#fff;background:#425470;background:linear-gradient(#425470, #5b6d8a);}
        .custom_typecontrol .selected_btn:hover {color:#fff;}
        .custom_zoomcontrol {position:absolute;top:50px;right:10px;width:36px;height:80px;overflow:hidden;z-index:1;background-color:#f5f5f5;}
        .custom_zoomcontrol span {display:block;width:36px;height:40px;text-align:center;cursor:pointer;}
        .custom_zoomcontrol span img {width:15px;height:15px;padding:12px 0;border:none;}
        .custom_zoomcontrol span:first-child{border-bottom:1px solid #bfbfbf;}
    </style>
</head>
<body>
<center>
<h1>어따하지</h1>
    <div class="map_wrap">
        <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
        <!-- 지도타입 컨트롤 div 입니다 -->
        <div class="custom_typecontrol radius_border">
            <span id="btnRoadmap" class="selected_btn" onclick="setMapType('roadmap')">지도</span>
            <span id="btnSkyview" class="btn" onclick="setMapType('skyview')">스카이뷰</span>
        </div>
        <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
        <div class="custom_zoomcontrol radius_border">
            <span onclick="zoomIn()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" alt="확대"></span>
            <span onclick="zoomOut()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" alt="축소"></span>
        </div>
    </div>

</center>
<!-- kakao 지도 API 가져오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bf58f6204f96c8bac33bd7aaeb780397"></script>
<script>
    <!-- 지도를 담을 div -->
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    //HTML5 geoloaction 사용
    if (navigator.geolocation) {

        //Geoloaction 이용해서 접속 위치 얻어오기.
        navigator.geolocation.getCurrentPosition(function(position){

            var lat = position.coords.latitude, //위도
                lon = position.coords.longitude; //경도

            var locPosition = new kakao.maps.LatLng(lat, lon),
                message = '<div style="padding:5px;">내 위치~</div>';

            displayMarker(locPosition, message);
        });
    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
            message = 'geolocation을 사용할수 없어요..'

        displayMarker(locPosition, message);
    }

    let data = [
        [37.298017889594675, 126.97102916721747, '<div style="padding:5px;">율전공영주차장</div>'],
        [37.301098863772786, 126.97141540531281, '<div style="padding:5px;">성대역환승주차장</div>']
    ]
    for(let value of data) {
        var locPosition = new kakao.maps.LatLng(value[0], value[1]),
            message = value[2]
        displayMarker(locPosition,message)
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

    // 지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
    function setMapType(maptype) {
        var roadmapControl = document.getElementById('btnRoadmap');
        var skyviewControl = document.getElementById('btnSkyview');
        if (maptype === 'roadmap') {
            map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
            roadmapControl.className = 'selected_btn';
            skyviewControl.className = 'btn';
        } else {
            map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
            skyviewControl.className = 'selected_btn';
            roadmapControl.className = 'btn';
        }
    }

    // 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
    function zoomIn() {
        map.setLevel(map.getLevel() - 1);
    }

    // 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다.
    function zoomOut() {
        map.setLevel(map.getLevel() + 1);
    }
</script>
</body>
</html>