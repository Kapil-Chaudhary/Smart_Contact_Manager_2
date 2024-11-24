package com.scm.services;


import com.scm.entities.Contact;
import com.scm.entities.User;

import java.util.List;

public interface ContactService {

    // save contact
    public Contact save(Contact contact);

    // update contact
    Contact update(Contact contact);

    // get contact
    List<Contact> getAll();

    // get contact by id
    Contact getById(String id);

    // delete contact
    void delete(String id);

    // search contact
    List<Contact> search(String name, String email, String phoneNumber);

    // get contact by user id
    List<Contact> getByUserId(String userId);

    List<Contact> getByUser(User user);

}
