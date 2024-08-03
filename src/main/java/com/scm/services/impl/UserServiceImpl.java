package com.scm.services.impl;

import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repository.UserRepo;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {

        // user id : we have to generate user id
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // password encode
        // user.setPassword(userId)

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getPassword().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));

        // update user2 from user
        user2.setEmail(user.getEmail());
        user2.setName(user.getName());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());


        // set values by builder , it's require for object creation
//        user2.builder()
//                .email(user.getEmail())
//                .name(user.getName())
//                .password(user.getPassword())
//                .about(user.getAbout())
//                .phoneNumber(user.getPhoneNumber())
//                .profilePic(user.getProfilePic())
//                .enabled(user.isEnabled())
//                .emailVerified(user.isEmailVerified())
//                .phoneVerified(user.isPhoneVerified())
//                .provider(user.getProvider())
//                .providerUserId(user.getProviderUserId())
//                .build();

        // save the user in database
        User saveUser = userRepo.save(user2);
        return Optional.ofNullable(saveUser);
    }

    @Override
    public void deleteUser(String id) {
        // 1
//        userRepo.deleteById(id);

        // 2
        User user2 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!!"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExists(String id) {
        User user = userRepo.findById(id).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }



    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
}



