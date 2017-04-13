/**
 * Created by souporman on 4/13/17.
 */

(function(window, google){

    var options = {
        center:{
            lat: 38.6184990,
            lng: -77.2934160
        },
        zoom: 10,
        disableDefulatUI:true,
        scrollwheel:false,
        draggable:false,
        mapTypeId:google.maps.MapTypeId.ROADMAP
    },
    element = document.getElementById('googleMap'),

    map = new google.maps.Map(element,options);

}(window, google));