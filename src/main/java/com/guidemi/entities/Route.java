package com.guidemi.entities;

import javax.persistence.*;


@Entity
@Table(name = "user_routes_saved")
public class Route {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    Users user;

    public Route() {
    }
    //IN here I want to try and code the routes to each person this will also be its own database;

//    origin: LatLng | String | google.maps.Place,
//    destination: LatLng | String | google.maps.Place,
//    travelMode: TravelMode,
//    transitOptions: TransitOptions,
//    drivingOptions: DrivingOptions,
//    unitSystem: UnitSystem,
//    waypoints[]: DirectionsWaypoint,
//    optimizeWaypoints: Boolean,
//    provideRouteAlternatives: Boolean,
//    avoidHighways: Boolean,
//    avoidTolls: Boolean,
//    region: String
}
//TODO grab the pictures