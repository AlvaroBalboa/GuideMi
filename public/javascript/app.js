var myApp = angular.module('mapApp', ['google-maps']);

myApp.controller('mainController', function($scope, $document) {

    //map object
    $scope.map = {
        control: {},
        center: {
            latitude: 38.6184990,
            longitude: -77.2934160
        },
        zoom: 7
    };

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

    // marker object
    // $scope.marker = {
    //     center: {
    //         latitude: -37.812150,
    //         longitude: 144.971008
//     //     }
    // }

    // instantiate google map objects for directions
    var directionsDisplay = new google.maps.DirectionsRenderer();
    var directionsService = new google.maps.DirectionsService();
    var geocoder = new google.maps.Geocoder();

    // instantiates auto complete onto the search bars
    var options = {
        componentRestrictions: {country: 'us'}
    };
    var input = document.getElementById('origin');
    var otherinput = document.getElementById('destination');
    autocomplete = new google.maps.places.Autocomplete(input,options);
    autocomplete = new google.maps.places.Autocomplete(otherinput,options);

    $scope.directionsResponse = '';
    // directions object -- with defaults
    $scope.directions = {
        origin: "",
        destination: "",
        showList: false
    };

    // get directions using google maps api
    $scope.getDirections = function () {
        var request = {
            origin: $scope.directions.origin,
            destination: $scope.directions.destination,
            travelMode: google.maps.DirectionsTravelMode.DRIVING
        };
        directionsService.route(request, function (response, status) {
            if (status === google.maps.DirectionsStatus.OK) {
                directionsResponse=response;
                directionsDisplay.setDirections(response);
                directionsDisplay.setMap($scope.map.control.getGMap());
                directionsDisplay.setPanel(document.getElementById('directionsList'));
                $scope.directions.showList = true;
            } else {
                alert('Google route unsuccesfull!');
            }
        });
    }

    // $scope.attraction_type = document.getElementById('attraction_type');

    service = new google.maps.places.PlacesService($scope.map);

    // POI
    // this is an change function that recalls the directions api for lat long info
    $scope.getAttraction = function () {

        var steps = directionsResponse.routes[0].legs[0].steps;

        // this needs to have a var that has the current leg lat long
        for (var i = 0; i < steps.length; i++) {
            var lng = directionsResponse.routes[0].legs[0].steps[i].end_location.lng();
            var lat = directionsResponse.routes[0].legs[0].steps[i].end_location.lat();

            console.log("making a request with lng, lat: " + lng + " " + lat);
           // console.log("making a request with lng: " + lng);


            var request = {
                location: {
                    lat: lat,
                    lng: lng
                },
                radius: '500',
                type: [$scope.attraction_type]
            };

            console.log("request for attraction type: " + request.type);

            service.nearbySearch(request, attraction_search);

        }
    }

    function attraction_search(results, status) {

        alert("attraction_search status: " +  status);
        console.log(results);
        // THIS IS FOR HANDELING ERRORS
        if (status !== google.maps.places.PlacesServiceStatus.OK) {
            console.error(status);
            return;
        }
        // // THIS SECTION WILL POPULATE THE DIV FOR THE RESPECTIVE TYPE
        // for (var i = 0; i < results[i]; i++) {
        //     populateDiv(result);
        // }
    }




});
