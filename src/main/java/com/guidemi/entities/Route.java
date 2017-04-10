package com.guidemi.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by souporman on 4/2/17.
 */
@Entity
@Table(name = "user_routes_saved")
public class Route {

    @ManyToOne
    Users user;
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
