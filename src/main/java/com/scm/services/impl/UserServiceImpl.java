package com.scm.services.impl;

import com.scm.entities.User;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {


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
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(String s) {
        return null;
    }

    @Override
    public User getById(String s) {
        return null;
    }

    @Override
    public User getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public List<User> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public Optional<User> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


    // ------------------------------------------------
    // i have added these 2 methods

    @Override
    public Optional<User> findByEmail(String email) {
//        userRepo.findByEmail(email);

        return Optional.empty();

    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return Optional.empty();
    }
}
