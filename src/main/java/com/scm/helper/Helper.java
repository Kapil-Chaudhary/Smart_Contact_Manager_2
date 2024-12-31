package com.scm.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class Helper {

    public static  String getEmailOfLoggedinUser(Authentication authentication) {


        // agar email or password se login kia hai toh : email kaise nikalenge
        if (authentication instanceof OAuth2AuthenticationToken) {

            OAuth2AuthenticationToken authentication1 = (OAuth2AuthenticationToken) authentication;
            var clientId = authentication1.getAuthorizedClientRegistrationId();
            var oAuthuser = (OAuth2User) authentication1.getPrincipal();

            String userName = "";

            if (clientId.equalsIgnoreCase("google")) {
                // sign in with Google
                System.out.println("Getting email from google client");
                userName = oAuthuser.getAttribute("email").toString();

            } else if (clientId.equalsIgnoreCase("github")) {
                // sign with github
                System.out.println("Getting email from github client");
                userName = oAuthuser.getAttribute("email") != null ?
                                                oAuthuser.getAttribute("email").toString()
                                                : oAuthuser.getAttribute("login").toString()+"@gmail.com";

            }
            return userName;

            // sign with facebook
        }
        else {
            System.out.println("getting data from local database");
            return authentication.getName();
        }

    }

    // http://localhost:8081/auth/varify-email?token=a5ecf604-77fe-44c4-8c65-298f205a8e23
    public static String getEmailForEmailVerification(String emailToken) {
        String link = "http://localhost:8081/auth/varify-email?token=" + emailToken;
        return link;
    }
}
