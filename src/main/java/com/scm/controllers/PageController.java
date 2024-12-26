package com.scm.controllers;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;

@Controller
public class PageController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }


    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handler!!");

        // sending data to view
        model.addAttribute("name", "SubString technologies");
        model.addAttribute("YoutubeChannel", "Learn Code with Durgesh");
        model.addAttribute("GithubRepo", "https://github.com/Durgesh");
        model.addAttribute("age", 25);

        return "home";
    }


    // about route
    @RequestMapping("/about")
    public String aboutPage(Model model){
        System.out.println("about page loading");
        model.addAttribute("isLogin", true);
        return "about";
    }


    // services route
    @RequestMapping("/services")
    public String servicesPageLoading(){
        System.out.println("services page loading");
        return "services";
    }

    // contact page
    @GetMapping("/contact")
    public String contact(){
        return new String("contact");
    }



    // this is login view
    // login page
    @GetMapping("/login")
    public String login(){
        return new String("login");
    }


    // this is registration page
    // register page
    @GetMapping("/register")
    public String register(Model model){

        // create object of user form
        UserForm userForm = new UserForm();
        // default data bhi daal sakte hai
//        userForm.setAbout("This is about : write something about yourself...");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    // processing register
    @RequestMapping(value="/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing registration...");

        // fetch from data
        // validate the data
        // save to database
        // message = "Registration Successful"
        // redirect to login page

        // ------------------------------------------------------------------------

        // fetch from data
        // UserForm
        System.out.println(userForm);


        // validate the data
        // todo : validate UserForm next video
        if (rBindingResult.hasErrors() ) {
            return "register";
        }




        // save to database -- user service
        // userForm --> user
        // main builder use nhi karnga bcos isme default value nhi aa rhi hai
//        User user = User.builder()
//                .name(userForm.getName())
//                .email(userForm.getEmail())
//                .password(userForm.getPassword())
//                .about(userForm.getAbout())
//                .phoneNumber(userForm.getPhoneNumber())
//                .profilePic("https://learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Flcwd_logo.45da3818.png&w=640&q=75")
//                .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Flcwd_logo.45da3818.png&w=640&q=75");

        User saveUser = userService.saveUser(user);
        System.out.println("user saved ");
        System.out.println("user save : " + saveUser);

        // message = "Registration Successful"
        // add the message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        // redirect to login page
        return "redirect:/register";
    }

}

