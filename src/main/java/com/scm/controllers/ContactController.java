package com.scm.controllers;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;
import com.scm.services.impl.ContactServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

//    private final ContactServiceImpl contactServiceImpl;
//
//    public ContactController(ContactServiceImpl contactServiceImpl) {
//        this.contactServiceImpl = contactServiceImpl;
//    }


    private Logger logger = LoggerFactory.getLogger(ContactController.class);


    @Autowired
    private ImageService imageService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    // add contact page handler
    @RequestMapping("/add")
    public String addContactView(Model model){

        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Authentication authentication, HttpSession session){

        // get user from authentication
        String username = Helper.getEmailOfLoggedinUser(authentication); // here email == username
        User user = userService.getUserByEmail(username);

        // process the form data

        // 1. validate form
        // TODO : add validation logic here
        if ( bindingResult.hasErrors() ) {
            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                            .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }


        // 2. process the contact picture
        // image process
        logger.info("file information : {}", contactForm.getContactImage().getOriginalFilename());
        // upload karna ka code

        String filename = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);



        // form ---> contact
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user); // ---------- get user from userService
        contact.setLinkedinLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);

        contactService.save(contact);
        System.out.println(contactForm);

        // 3. set the contact picture URL

        // 4. set message to be displayed on the view
        session.setAttribute("message", Message.builder()
                .content("You have successfully added new contact")
                .type(MessageType.green)
                .build());

        return "redirect:/user/contacts/add";
    }

}
