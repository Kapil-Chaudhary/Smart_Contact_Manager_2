package com.scm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Autowired
    private SecurityCustomUserDetailService userDetailsService;


    // daoAuthenticationProvider k pass vo sabhi methods hai jiski help se hum apni service register(ya authenticate) kar skte hai
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService); // userDetailsService ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // password encoder ka object
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // url configure kiay hai ki koun se public ranhenge aur konse private rahenge
        httpSecurity.authorizeHttpRequests( authorized -> {
//            authorized.requestMatchers("/home", "/register", "/services").permitAll();
            authorized.requestMatchers("/user/**").authenticated();
            authorized.anyRequest().permitAll();
        });

        // form default login
        // agar kuch bhi change karna hua to hum yaha aayenge : form login se related
        httpSecurity.formLogin(Customizer.withDefaults());

        DefaultSecurityFilterChain build = httpSecurity.build();
        return build;
    }

}



















