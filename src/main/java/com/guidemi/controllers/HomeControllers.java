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

    public static final String REDIRECT = "http://localhost:8080";

    public static final String SESSION_USERNAME= "username";

    @Value("${facebook.secret}")
    public String appSecret;

    @Value("${facebook.app-id}")
    public String appId;

    @Autowired
    RestTemplate restTemplate;

    public String appAccessToken;

    @PostConstruct
    public void facebookToken(){
        String tokenGetUrl= "http://graph.facebook.com/oauth/access_token" +
                "    ?client_id=%s" +
                "    &client_secret=%s" +
                "    &grant_type=client_credentials";


        String appTokenResults = restTemplate.getForObject(String.format(tokenGetUrl,appId,appSecret),String.class);
        String [] tokenMatrixResult = appTokenResults.split("=");
        appAccessToken = tokenMatrixResult[1];

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model,HttpSession session){

        model.addAttribute("username",session.getAttribute(SESSION_USERNAME));
        model.addAttribute("appId",appId);
        model.addAttribute("redirect",REDIRECT);
        return "index";
    }


    @RequestMapping(path = "/login",method = RequestMethod.GET)
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
