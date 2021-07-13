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
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도 초기 중심좌표
        level: 3 // 지도 확대 레벨
    };
    <!-- 지도를 생성 / 옵션과 지도를 담을 div로 -->
    var map = new kakao.maps.Map(container, options);
</script>
</body>
</html>