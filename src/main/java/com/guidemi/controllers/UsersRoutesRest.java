package com.guidemi.controllers;

import com.guidemi.services.POIRepo;
import com.guidemi.services.RouteRepo;
import com.guidemi.services.UserRepo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/{userId}/allRoutes",method = RequestMethod.GET)
    public List template(@PathVariable("userId") int userId){
        return routes.findByUserId(userId);
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
