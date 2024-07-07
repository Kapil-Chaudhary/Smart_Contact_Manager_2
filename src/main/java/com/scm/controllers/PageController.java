package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
