package com.scm.config;

import com.scm.entities.User;
import com.scm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // apne user ko load karna hai
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found with email : " + username));
        return user;
    }
}
