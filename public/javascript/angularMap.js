/**
 * Created by souporman on 4/16/17.
 */
var app = angular.module('plunker', ['google-maps']);

app.controller('MainCtrl', function($scope, $document) {
    // map object
    $scope.map = {
        control: {},
        center: {
            latitude: -37.812150,
            longitude: 144.971008
        },
        zoom: 14
    };

    // marker object
    $scope.marker = {
        center: {
            latitude: -37.812150,
            longitude: 144.971008
        }
    }

    // instantiate google map objects for directions
    var directionsDisplay = new google.maps.DirectionsRenderer();
    var directionsService = new google.maps.DirectionsService();
    var geocoder = new google.maps.Geocoder();

    var defaultBounds = new google.maps.LatLngBounds(
        new google.maps.LatLng(-33.8902, 151.1759),
        new google.maps.LatLng(-33.8474, 151.2631));

    var input = document.getElementById('origin');
    var options = {
        bounds: defaultBounds,
        types: ['establishment'],
        componentRestrictions: {country: 'fr'}
    };

    autocomplete = new google.maps.places.Autocomplete(input, options);

    // directions object -- with defaults
    $scope.directions = {
        origin: "Collins St, Melbourne, Australia",
        destination: "MCG Melbourne, Australia",
        showList: false
    }

    // get directions using google maps api
    $scope.getDirections = function () {
        var request = {
            origin: $scope.directions.origin,
            destination: $scope.directions.destination,
            travelMode: google.maps.DirectionsTravelMode.DRIVING
        };
        directionsService.route(request, function (response, status) {
            if (status === google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                directionsDisplay.setMap($scope.map.control.getGMap());
                directionsDisplay.setPanel(document.getElementById('directionsList'));
                $scope.directions.showList = true;
            } else {
                alert('Google route unsuccesfull!');
            }
        });
    }
});
