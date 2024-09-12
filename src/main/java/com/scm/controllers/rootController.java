package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class rootController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInfromation(Model model, Authentication authentication) {
        if ( authentication==null ) return;
        System.out.println("Adding logged in user information to the model");

        String username = Helper.getEmailOfLoggedinUser(authentication); // get current logged in "user-email"
        logger.info("user logged in : {} ", username);

        // database se data ko fetch : get user from DB : email, name, address
        User user = userService.getUserByEmail(username);
        System.out.println(user);

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);


    }

}
