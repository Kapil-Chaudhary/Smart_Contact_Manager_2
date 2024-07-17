package com.scm.services;

import com.scm.entities.User;
import com.scm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface UserService extends UserRepo {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExists(String id);
    boolean isUserExistsByEmail(String email);

    List<User> getAllUsers();


    // add more user methods here related user service[logic]

}
