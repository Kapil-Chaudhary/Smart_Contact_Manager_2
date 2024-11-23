package com.scm.services.impl;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repository.ContactRepo;
import com.scm.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with give id " + id));
    }

    @Override
    public void delete(String id) {
        Contact contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found with give id " + id));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

    @Override
    public List<Contact> getByUser(User user) {
        List<Contact> byUser = this.contactRepo.findByUser(user);
        return byUser;
    }
}
