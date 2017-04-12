package com.guidemi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by souporman on 4/2/17.
 */
@Controller
public class HomeControllers {

    //This is setting up a redirect for the dev cycle
    public static final String REDIRECT = "http://localhost:8080/userProfile";

    //I want to keep the same session getter and don't want it getting confussed with anything else
    public static final String SESSION_USERNAME= "username";

    //This grabs the app secret from the application properties
    @Value("${facebook.secret}")
    public String appSecret;

    //This grabs the app id from the application properties
    @Value("${facebook.app-id}")
    public String appId;

    //This bean was created in the Application class and this lets me use the functions associated with it
    @Autowired
    RestTemplate restTemplate;

    //This is an access token given to the app after the initial login token was recieved
    public String appAccessToken;

    //This uses the appId and the appSecret to get the app token
    @PostConstruct
    public void init(){
        String tokenGetUrl= "http://graph.facebook.com/oauth/access_token" +
                "    ?client_id=%s" +
                "    &client_secret=%s" +
                "    &grant_type=client_credentials";
        String appTokenResults = restTemplate.getForObject(String.format(tokenGetUrl,appId,appSecret),String.class);
        String [] tokenMatrixResult = appTokenResults.split("=");
        appAccessToken = tokenMatrixResult[1];
    }

    //This is a controller for the home page
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model,HttpSession session){

//        model.addAttribute("username",session.getAttribute(SESSION_USERNAME));
//        model.addAttribute("appId",appId);

        model.addAttribute("redirect",REDIRECT);
        return "index";
    }

    //This is a controller for the userProfile page I want to pre fill out if the create user form
    @RequestMapping(path = "/userProfile", method = RequestMethod.GET)
    public String userPage(Model model, HttpSession session){
        model.addAttribute("");
        return "userProfile";
    }

    //This is the route that the facebook Oauth will take to hand out the user information
    @RequestMapping(path = "/userProfile/login",method = RequestMethod.GET)
    public String facebookLogin(String code, HttpSession session){
        String tokenURL="https://graph.facebook.com/v2.8/oauth/access_token?" +
                "   client_id=%s}" +
                "   &redirect_uri=%s" +
                "   &client_secret=%s" +
                "   &code=%s";
        Map atResults = restTemplate.getForObject(String.format(tokenURL,appId,REDIRECT,appSecret,code),HashMap.class);
        String tokenChecker = "https://graph.facebook.com/debug_token?" +
                "     input_token=%s" +
                "     &access_token=%s";

        //Weird checker is not user to validate but instead used to hold info
        Map checker = restTemplate.getForObject(String.format(tokenChecker,atResults.get("access_token"),appAccessToken),HashMap.class);

        //Check the is valid value
        //the

        //in the field area you can place the exact fields that I need to store
        Map userResults = restTemplate.getForObject("https://graph.facebook.com/v2.8/me?fields=id,name&access_token="+atResults.get("access_token"),HashMap.class);

        session.setAttribute(SESSION_USERNAME,userResults.get("name"));
        //check the API explorer on facebook

        return"redirect:/";
    }

}
