package com.guidemi.controllers;

import com.fasterxml.jackson.core.*;
import com.guidemi.services.POIRepo;
import com.guidemi.services.RouteRepo;
import com.guidemi.services.UserRepo;
import io.swagger.annotations.Api;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by souporman on 4/2/17.
 */
@RestController
@RequestMapping("/rest")
@Api(value="userRoutes", description="these are rest apis for each specific user routes and current wayPoints")
public class UsersRoutesRest {

    @Autowired
    UserRepo users;

    @Autowired
    RouteRepo routes;

    @Autowired
    POIRepo pois;

    @Value("${google.api-key}")
    public String googleMapApiKey;

    @Value("${google.search-key}")
    public String googleSearchKey;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(path = "/{userId}/allRoutes",method = RequestMethod.GET)
    public List template(@PathVariable("userId") int userId){
        return routes.findByUserId(userId);
    }

    @RequestMapping(path = "/nearbySearch&{lat}&{lng}&{attraction_type}",method = RequestMethod.GET)
    public String getNearby(@PathVariable("lat") String lat,@PathVariable("lng") String lng,@PathVariable("attraction_type") String attraction_type){
        String placesSearch = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                "location=%s" +
                ",%s&" +
                "radius=500&" +
                "types=%s&" +
                "key=%s";

        String response = restTemplate.getForObject(String.format(placesSearch,lat,lng,attraction_type,googleSearchKey),String.class);

        return response;
    }
//    @RequestMapping(path = "/{userId}/",method = RequestMethod.GET)
//    public void template(){
//
//    }
//    @RequestMapping(path = "/{userId}/",method = RequestMethod.GET)
//    public void template(){
//
//    }
//    @RequestMapping(path = "/{}",method = RequestMethod.GET)
//    public void template(){
//
//    }
//    @RequestMapping(path = "/{}",method = RequestMethod.GET)
//    public void template(){
//
//    }

//    @Autowired
//    CitizenDataRepository citizenData;
//
//    //THESE ARE THE BASIC 3 THAT ARE GETTING AND POSTING AND PUTTING/REPLACING
//    @RequestMapping(path = "/citizen", method = RequestMethod.GET)
//    public List<CitizenData> getCitizens() {
//        return (List<CitizenData>) citizenData.findAll();
//    }
//
//    @RequestMapping(path = "/citizen", method = RequestMethod.POST)
//    public void addCitizen(@RequestBody CitizenData citizen) {
//        citizenData.save(citizen);
//    }
//
//    @RequestMapping(path = "/citizen", method = RequestMethod.PUT)
//    public void updateCitizen(@RequestBody CitizenData citizen) {
//        citizenData.save(citizen);
//    }
//
//    @RequestMapping(path = "/deleteCitizen/{id}", method = RequestMethod.DELETE)
//    public void deleteCitizen(@PathVariable("id") int id) {
//        citizenData.delete(id);
//    }
//
//    @RequestMapping(path = "/getOneCitizen/{id}", method = RequestMethod.GET)
//    public CitizenData getCitizen(@PathVariable("id") int id) {
//        return citizenData.findOne(id);
//    }

}
