/*
var markerPosition  = new kakao.maps.LatLng( 35.24627789502604, 128.9038623104839);

var marker = {
    position: markerPosition
};

var staticMapContainer  = document.getElementById('map'),
    staticMapOption = {
        center: new kakao.maps.LatLng( 35.24627789502604, 128.9038623104839),
        level: 3,
        marker: marker
    };

var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);

 */

// hospital_info.js
window.onload = function() {
    // 지도 표시할 div
    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(latitude, longitude),
        level: 3
    };

    // 지도 생성
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커 생성
    var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(latitude, longitude)
    });
    marker.setMap(map);

    // 정보창 생성 (마커 클릭 시 표시)
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="padding:5px;">' + hospitalName + '</div>'
    });

    // 마커 클릭 시 정보창 열기
    kakao.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map, marker);
    });
}