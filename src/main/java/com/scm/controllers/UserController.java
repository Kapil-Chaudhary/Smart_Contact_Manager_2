package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // user Dashboard page
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "user/dashboard";
    }


    // user profile page
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, Authentication authentication) {
        System.out.println("User Profile");



        return "user/profile";
    }


    // user add contact page

    // user view contact

    // user edit contact

    // user delete contact


}
