package com.scm.config;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.repository.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler -- runner");

        // identify the provider
        var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);

        var oAuthUser = (DefaultOAuth2User) authentication.getPrincipal();
        oAuthUser.getAttributes().forEach( (key,value) -> logger.info(key + " = " + value));

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("dummy");

        if ( authorizedClientRegistrationId.equalsIgnoreCase("google") ){
            // identify google login
            // google attributes
            user.setEmail(oAuthUser.getAttribute("email").toString());
            user.setProfilePic(oAuthUser.getAttribute("picture").toString());
            user.setName(oAuthUser.getAttribute("name").toString());
            user.setProviderUserId(oAuthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account is created using google..");

        }
        else if ( authorizedClientRegistrationId.equalsIgnoreCase("github") ){

            // GitHub
            // GitHub attributes
            String email = oAuthUser.getAttribute("email") != null ? oAuthUser.getAttribute("email").toString() : oAuthUser.getAttribute("login").toString()+"@gmail.com";
            String picture = oAuthUser.getAttribute("avatar_url").toString();
            String name = oAuthUser.getAttribute("login").toString();
            String providerUserId = oAuthUser.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);
            user.setAbout("This account is created using github!!");
        }
        else if ( authorizedClientRegistrationId.equalsIgnoreCase("facebook") ){
            // facebook
        }
        else if ( authorizedClientRegistrationId.equalsIgnoreCase("linkedIn") ){
            // LinkedIn
        }


        // save the user
        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {
            userRepo.save(user);
            logger.info("User Saved : " + user.getEmail());
        }


        /*


        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        logger.info("username : " + user.getName());
        user.getAttributes().forEach( (key, value) -> logger.info("{} => {} ",  key, value));
        logger.info("user authorities : " + user.getAuthorities().toString());


        // data database save
        String email = user.getAttribute("email");
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();


//        Map<String, Object> attributes = user.getAttributes();
//        attributes.forEach( (key, value) -> System.out.println(key + " - " + value.toString()) );


        // create user and save in database
        User user1 = new User();
        user1.setName(name);
        user1.setEmail(email);
        user1.setProfilePic(picture);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);

        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));
        user1.setAbout("This account is created using google..");

        User user2 = userRepo.findByEmail(email).orElse(null);
        if (user2 == null) {
            userRepo.save(user1);
            logger.info("User Saved : " + email);
        }


         */

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
