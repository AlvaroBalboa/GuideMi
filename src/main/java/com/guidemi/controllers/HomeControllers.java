package com.guidemi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by souporman on 4/2/17.
 */
@Controller
public class HomeControllers {

    @RequestMapping(value = "/")
    public String home(){
        return "index";
    }



}
