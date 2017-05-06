package com.guidemi.controllers;

import com.fasterxml.jackson.core.*;
import com.guidemi.entities.POI;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by souporman on 4/2/17.
 */
@RestController
@RequestMapping("/rest")
@Api(value="userRoutes", description="these are rest apis for each specific user routes and current wayPoints")
public class UsersRoutesRest {

    public List artArray = new ArrayList();
    public List museumArray = new ArrayList();
    public List casinoArray = new ArrayList();
    public List parkArray = new ArrayList();
    public List mosqueArray = new ArrayList();

//    public List artArray;
//    public List artArray;
//    public List artArray;
//    public List artArray;

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
    public void getNearby(@PathVariable("lat") String lat,@PathVariable("lng") String lng,@PathVariable("attraction_type") String attraction_type){
        String placesSearch = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                "location=%s" +
                ",%s&" +
                "radius=500&" +
                "types=%s&" +
                "key=%s";

//        https://developer.nps.gov/api/v0/parks??stateCode=VA
//        String npsSearch = "https://developer.nps.gov/api/v0/parks??stateCode=%s"
//        Map nosResponse=restTemplate.getForObject(String.format(npsSearch,state),HashMap.class);

        Map responseHash = restTemplate.getForObject(String.format(placesSearch,lat,lng,attraction_type,googleSearchKey),HashMap.class);
        String status = (String) responseHash.get("status");

//        I can do just one set of loops and use if statements to dot add into array
        if(attraction_type.equalsIgnoreCase("art_gallery")){
            List bravoSierra = new ArrayList();
            if(status.equalsIgnoreCase("ok")) {
                bravoSierra.add(responseHash.get("results"));
                for (int i = 0; i < bravoSierra.size(); i++) {
                    List object = (List) bravoSierra.get(i);
                    for (int l = 0; l < object.size(); l++) {
                        HashMap lastList = (HashMap) object.get(l);
                        artArray.add(lastList.get("name"));
                    }
                }
            }
        }
        if(attraction_type.equalsIgnoreCase("mosque")){
            List bravoSierra = new ArrayList();
            if(status.equalsIgnoreCase("ok")) {
                bravoSierra.add(responseHash.get("results"));
                for (int i = 0; i < bravoSierra.size(); i++) {
                    List object = (List) bravoSierra.get(i);
                    for (int l = 0; l < object.size(); l++) {
                        HashMap lastList = (HashMap) object.get(l);
                        mosqueArray.add(lastList.get("name"));
                    }
                }
            }
        }
        else if (attraction_type.equalsIgnoreCase("museum")){
            List bravoSierra = new ArrayList();
            if(status.equalsIgnoreCase("ok")) {
                bravoSierra.add(responseHash.get("results"));
                for (int i = 0; i < bravoSierra.size(); i++) {
                    List object = (List) bravoSierra.get(i);
                    for (int l = 0; l < object.size(); l++) {
                        HashMap lastList = (HashMap) object.get(l);
                        museumArray.add(lastList.get("name"));
                    }
                }
            }
        }
        else if (attraction_type.equalsIgnoreCase("casino")){
            List bravoSierra = new ArrayList();
            if(status.equalsIgnoreCase("ok")) {
                bravoSierra.add(responseHash.get("results"));
                for (int i = 0; i < bravoSierra.size(); i++) {
                    List object = (List) bravoSierra.get(i);
                    for (int l = 0; l < object.size(); l++) {
                        HashMap lastList = (HashMap) object.get(l);
                        casinoArray.add(lastList.get("name"));
                    }
                }
            }
        }
        else if (attraction_type.equalsIgnoreCase("park")){
            List bravoSierra = new ArrayList();
            if(status.equalsIgnoreCase("ok")) {
                bravoSierra.add(responseHash.get("results"));
                for (int i = 0; i < bravoSierra.size(); i++) {
                    List object = (List) bravoSierra.get(i);
                    for (int l = 0; l < object.size(); l++) {
                        HashMap lastList = (HashMap) object.get(l);
                        parkArray.add(lastList.get("name"));
                    }
                }
            }
        }
    }

    public void clearLists(){
       artArray.clear();
       museumArray.clear();
       casinoArray.clear();
       parkArray.clear();
       mosqueArray.clear();
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

    @RequestMapping(path = "/results/art_gallery",method = RequestMethod.GET)
    public List getArtGallery(){
        return artArray;
    }
    @RequestMapping(path = "/results/museum",method = RequestMethod.GET)
    public List getMuseumArray(){
        return museumArray;
    }
    @RequestMapping(value = "/results/casino",method = RequestMethod.GET)
    public List getCasinoArray(){
        return casinoArray;
    }
    @RequestMapping(value = "/results/mosque",method = RequestMethod.GET)
    public List getMosqueArray(){
        return mosqueArray;
    }
    @RequestMapping(value = "/results/park",method = RequestMethod.GET)
    public List getParkArray(){
        return parkArray;
    }
}
