package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    // user Dashboard page
    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String dashboard() {
        return "user/dashboard";
    }


    // user profile page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile() {
        System.out.println("User Profile");
        return "user/profile";
    }


    // user add contact page

    // user view contact

    // user edit contact

    // user delete contact


}
