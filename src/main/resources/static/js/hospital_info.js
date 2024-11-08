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
