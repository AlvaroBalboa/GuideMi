/**
 * Created by souporman on 4/13/17.
 */

var x;

(function (window, google) {
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer;
    var map = new google.maps.Map(document.getElementById('googleMap'), {
        zoom: 10,
        disableDefaultUI:true,
        scrollwheel:false,
        draggable:false,
        center: {lat: 38.6184990, lng: -77.2934160}
    });
    directionsDisplay.setMap(map);

    document.getElementById('submit').addEventListener('click', function() {
        calculateAndDisplayRoute(directionsService, directionsDisplay);
    });
}(window, google));

// This is a function to get your current location based off of IP address // html
// Work in progress
// function getLocation() {
//     if (navigator.geolocation) {
//         x = navigator.geolocation;
//     }
//     else {
//         console.log("Geolocation is not supported by this browser.");
// }

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
    var waypts = [];
    var checkboxArray = document.getElementById('waypoints');
    for (var i = 0; i < checkboxArray.length; i++) {
        if (checkboxArray.options[i].selected) {
            waypts.push({
                location: checkboxArray[i].value,
                stopover: true
            });
        }
    }

    directionsService.route({
        origin: document.getElementById('start').value,
        destination: document.getElementById('end').value,
        waypoints: waypts,
        optimizeWaypoints: true,
        travelMode: 'DRIVING'
    }, function(response, status) {
        if (status === 'OK') {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions-panel');
            summaryPanel.innerHTML = '';
            // For each route, display summary information.
            for (var i = 0; i < route.legs.length; i++) {
                var routeSegment = i + 1;
                summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment +
                    '</b><br>';
                summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
                summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
                summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
            }
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}
//
// (function(window, google){
//
//     var options = {
//         center:{
//             lat: 38.6184990,
//             lng: -77.2934160
//         },
//         zoom: 10,
//         disableDefaultUI:true,
//         scrollwheel:false,
//         draggable:false,
//         mapTypeId:google.maps.MapTypeId.ROADMAP
//     },
//     element = document.getElementById('googleMap'),
//
//     map = new google.maps.Map(element,options);
//     window.resize(function() {
//         // (the 'map' here is the result of the created 'var map = ...' above)
//         google.maps.event.trigger(map, "resize");
//     });
// }(window, google));
