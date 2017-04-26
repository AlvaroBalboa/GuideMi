var myApp = angular.module('mapApp', ['google-maps']);

myApp.controller('mainController', ['$scope', '$http','$document', function($scope, $http, $document) {

    //map object
    $scope.map = {
        control: {},
        center: {
            latitude: 38.6184990,
            longitude: -77.2934160
        },
        zoom: 7
    };

    $scope.options = {
        scrollwheel:false,
        disableDefaultUI: true
    };

    // instantiate google map objects for directions
    var directionsDisplay = new google.maps.DirectionsRenderer();
    var directionsService = new google.maps.DirectionsService();
    var geocoder = new google.maps.Geocoder();
    var markers = [];
    $scope.randomMarkers = markers;
    // instantiates auto complete onto the search bars
    var autoCompleteOptions = {
        componentRestrictions: {country: 'us'}
    };
    var input = document.getElementById('origin');
    var otherinput = document.getElementById('destination');
    autocomplete = new google.maps.places.Autocomplete(input,autoCompleteOptions);
    autocomplete = new google.maps.places.Autocomplete(otherinput,autoCompleteOptions);

    $scope.directionsResponse = '';

    $scope.directions = {
        origin: "",
        destination: "",
        showList: false
    };

    var idKey = 'default';

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
    };

    // this is an change function that recalls the directions api for lat long info
    $scope.getAttraction = function () {

        var steps = directionsResponse.routes[0].legs[0].steps;

        for (var i = 0; i < steps.length; i++) {
            var lng = directionsResponse.routes[0].legs[0].steps[i].end_location.lng();
            var lat = directionsResponse.routes[0].legs[0].steps[i].end_location.lat();

            $http.get('/rest/nearbySearch&'+lat+'&'+lng+'&'+$scope.attraction_type).then(function(response){

                // Marker functionality
                // var createMarker = function(i,idKey) {
                //     var ret = {
                //         latitude: response.data.results[i].geometry.location.lat,
                //         longitude: response.data.results[i].geometry.location.lng,
                //         title: 'm' + i,
                //         icon: '/img/museum_paintings.png'
                //     };
                //     ret[idKey] = i;
                //     console.log(idKey);
                //     console.log(ret);
                //     return ret;
                // };
                //
                // if (response.data.status === 'OK') {
                //     if ($scope.attraction_type === 'art_gallery'){
                //
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //         // then open div and add new markers
                //         $scope.list_art_gallery = response.data.results;
                //
                //         console.log($scope.list_art_gallery);
                //
                //         // for (var i = 0; i < response.data.results.length; i++) {
                //         //     markers.push(createMarker(i,'art_galleries'))
                //         // }
                //
                //         // $scope.randomMarkers = markers;
                //
                //     }
                //     if ($scope.attraction_type === 'mosque'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'museum'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'casino'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'park'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'church'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'stadium'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'synagogue'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'zoo'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'hindu_temple'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'anotherone'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'anotherone'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'another'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'another'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'another'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //     if ($scope.attraction_type === 'another'){
                //         // I want this area to hold logic to open up the div
                //         // of points and
                //         // maybe create markers but they must be deleted
                //         // once another scope.attraction_type is chosen.
                //
                //         // FIRST clear the old markers
                //
                //
                //         // then open div and add new markers
                //     }
                //
                // // alert("attraction_search status: " +  response.status);
                //     return;
                // }
                //
                // console.error(status);

            });
        }
    };

}]);
