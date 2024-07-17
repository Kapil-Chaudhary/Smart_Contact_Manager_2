package com.scm.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PractiseController {

    @RequestMapping("/test")
    public String test(Model model){
        System.out.println("this is test page!!");
        System.out.println("----");
        model.addAttribute("name", "kapil chaudhary");
        return "test";
    }


    @RequestMapping("/test2")
    public String test2(){
        System.out.println("kapil");
        System.out.println("akash");
        return "test";

    }

}