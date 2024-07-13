package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

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

    // login page
    @GetMapping("/login")
    public String login(){
        return new String("login");
    }

    // register page
    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
