// /**
//  * Created by souporman on 4/16/17.
//  */
//
// var map;
// var service;
// var directionsDisplay;
// var directionsService = new google.maps.DirectionsService();
// // var z = document.getElementById("googleMap");
// // var start;
// // var end;
// var mapOptions;
// // var currentLocation = new google.maps.LatLng(location.cords.latitude,location.cords.longitude);
// //
// // if ( currentLocation == null)
// // {
// //     currentLocation = {lat: 38.6184990, lng: -77.2934160}
// // }
// var autocompleteStart;
// var autocompleteEnd;
//
// function initialize() {
//
//     mapOptions ={
//         center:{lat: 38.6184990, lng: -77.2934160},
//         disableDefaultUI:true,
//         scrollwheel:false,
//         draggable:false,
//         zoom: 7
//     };
//
//     map = new google.maps.Map(document.getElementById('googleMap'),mapOptions);
//     service = new google.maps.places.PlacesService(map);
//     autocompleteStart = new google.maps.places.Autocomplete(document.getElementById('startLocation'));
//     autocompleteEnd = new google.maps.places.Autocomplete(document.getElementById('endLocation'));
//     autocompleteStart.bindTo('bounds',map);
//     autocompleteEnd.bindTo('bounds',map);
//     directionsDisplay = new google.maps.DirectionsRenderer({
//         // draggable: true,
//         map: map,
//         panel: document.getElementById('directionsPanel')
//     });
//
//     directionsDisplay.addListener('directions_changed', function() {
//         computeTotalDistance(directionsDisplay.getDirections());
//     });
//
//     displayRoute(
//         document.getElementById('startLocation'),
//         document.getElementById('endLocation')
//     // 'Woodbridge, VA', 'Arlington, VA'
//         , directionsService,
//         directionsDisplay);
//
//     google.maps.event.addListenerOnce(map,"#refresh",preformSearch());
//     $('#map-it').click(preformSearch());
// }
//
// function displayRoute(origin, destination, service, display) {
//     service.route({
//         origin: origin,
//         destination: destination,
//         // waypoints: [{location: 'Adelaide, SA'}, {location: 'Broken Hill, NSW'}],
//         travelMode: 'DRIVING',
//         avoidTolls: true
//     }, function(response, status) {
//         if (status === 'OK') {
//             display.setDirections(response);
//         } else {
//             alert('Could not display directions due to: ' + status);
//         }
//     });
// }
//
// function computeTotalDistance(result) {
//     var total = 0;
//     var myroute = result.routes[0];
//     for (var i = 0; i < myroute.legs.length; i++) {
//         total += myroute.legs[i].distance.value;
//     }
//     total = total / 1000;
//     document.getElementById('total').innerHTML = total + ' km';
// }
//
// function searchResults(results,status) {
//     console.log(results)
//     for(var i=0;i<results.length;i++){
//         var marker = new google.maps.Marker({
//             position:results[1].geometry.location,
//             map:map
//             });
//     }
// }
// function preformSearch(result,attraction) {
//
//     // loop for legs location
//     var total = 0;
//     var myroute = result.routes[0];
//     var legSection;
//
//     for (var i = 0; i < myroute.legs.length; i++) {
//         location: myroute.legs[i];
//
//         var request = {
//             legSection:location,
//             name:attraction
//         };
//
//         service.nearbySearch(request, searchResults());
//     }
// }
//
// // function showDirectionsPosition(position) {
// //     console.log("showDirectionsPosition");
// //     directionsLatitude = position.coords.latitude;
// //     directionsLongitude = position.coords.longitude;
// //     directionsLatLng = new google.maps.LatLng(directionsLatitude,directionsLongitude);
// //     getDirections();
// // }
// //
// // function getDirections() {
// //     console.log('getDirections');
// //     directionsDisplay = new google.maps.DirectionsRenderer();
// //     var directionsOptions = {
// //         zoom:12,
// //         center: start
// //     };
// //
// //     directionsMap = new google.maps.Map(document.getElementById("googleMap"), directionsOptions);
// //     directionsDisplay.setMap(directionsMap);
// //     calcRoute();
// // }
// //
// // function calcRoute() {
// //     console.log("calcRoute");
// //     start = document.getElementById("startLocation");
// //     end = document.getElementById("endLocation");
// //     var request = {
// //         origin:start,
// //         destination:end,
// //         travelMode: google.maps.TravelMode.TRANSIT
// //     };
//
// //     directionsService.route(request, function(result, status) {
// //         if (status == google.maps.DirectionsStatus.OK) {
// //             directionsDisplay.setDirections(result);
// //         }
// //     });
// // }
//
// $(document).ready(initialize ()
//     // navigator.geolocation.getCurrentPosition(initialize);
//     // getDirectionsLocation();
// );