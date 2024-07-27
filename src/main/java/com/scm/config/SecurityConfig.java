package com.scm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {


    // in-memory database
    // user create and login using java code with in memory service
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.withDefaultPasswordEncoder().username("admin123").password("123").roles("ADMIN", "User").build();
//        UserDetails user2 = User.withDefaultPasswordEncoder().username("user123").password("123").build();
//
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(user1, user2);
//        return manager;
//    }




    @Autowired
    private SecurityCustomUserDetailService userDetailsService;


    // daoAuthenticationProvider k pass vo sabhi methods hai jiski help se hum apni service register kar skte hai
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // userDetailsService ka object
        provider.setUserDetailsService(userDetailsService);

        // password encoder ka object
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
