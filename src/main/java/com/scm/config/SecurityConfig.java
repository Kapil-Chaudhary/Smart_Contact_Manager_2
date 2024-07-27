package com.scm.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {


    // user create and login using java code with in memory service
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User.withDefaultPasswordEncoder().username("admin123").password("123").roles("ADMIN", "User").build();
        UserDetails user2 = User.withDefaultPasswordEncoder().username("user123").password("123").build();


        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(user1, user2);
        return manager;
    }

}
