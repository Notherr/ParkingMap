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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bf58f6204f96c8bac33bd7aaeb780397&libraries=clusterer"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    <!-- 지도를 담을 div -->
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 6 // 클러스터 할 최소 지도 레벨
    });

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
    let markers = []

    for(let value of data) {
        var locPosition = new kakao.maps.LatLng(value[0], value[1]),
            message = value[2]
        displayMarker(locPosition,message)
    }
    clusterer.addMarkers(markers);

    $( document ).ready(function() { // document의 ready 메소드를 이용하여 html 요소와 javascript 동적 실행의 순서를 보장해준다. 즉 이 안의 코드는 문서가 모두 로드되고 실행된다.
        $.ajax({
            url:'http://localhost:8080/apitest3',
            type:'GET',
            dataType:'text', // 리턴해주는 타입을 지정해줘야함
            beforeSend:function(jqXHR) {
                console.log("requesting ajax..");
            },// 서버 요청 전 호출 되는 함수 return false; 일 경우 요청 중단
            success: function(data) {
                console.log("reguest success");
                //console.log(JSON.parse(data));
                var data = JSON.parse(data); // 반환된 데이터 형식을 다시 JSON형태로 바꿔주니 데이터 값 조회가 잘 된다.
                // console.log(data.response.body.items[0]) // {} 는 .으로 [](배열)은 []으로 조회
                console.log(data.response.body.items.length)

                for(let i = 0; i<data.response.body.items.length; i++) {
                    var locPosition = new kakao.maps.LatLng(parseFloat(data.response.body.items[i]['latitude']), parseFloat(data.response.body.items[i]['longitude'])),
                        message = data.response.body.items[i]['prkplceNm']
                    displayMarker(locPosition,message)
                }
                clusterer.addMarkers(markers);

            },// 요청 완료 시
            error:function(jqXHR) {
                console.log("request failed");
            }// 요청 실패.
        });
    });

    // 장소 검색 객체를 생성합니다
    var ps = new kakao.maps.services.Places();

    // 키워드로 장소를 검색합니다
    ps.keywordSearch('이태원 맛집', placesSearchCB);

    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    function placesSearchCB (data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            var bounds = new kakao.maps.LatLngBounds();

            for (var i=0; i<data.length; i++) {
                displayMarker2(data[i]);
                bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
            }

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
        }
    }
    // 검색을 통한 위치를 지도에 마커를 표시하는 함수입니다.
    function displayMarker2(place) {

        // 마커를 생성하고 지도에 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x)
        });

        var infowindow = new kakao.maps.InfoWindow({zIndex:1});

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function() {
            // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
            infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
            infowindow.open(map, marker);
        });
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

        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);

        markers.push(marker);

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다$
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
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

    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    function makeOverListener(map, marker, infowindow) {
        return function() {
            infowindow.open(map, marker);
        };
    }

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    function makeOutListener(infowindow) {
        return function() {
            infowindow.close();
        };
    }

</script>
</body>
</html>