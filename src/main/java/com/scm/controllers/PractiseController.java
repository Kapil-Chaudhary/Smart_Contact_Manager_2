package com.scm.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PractiseController {

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("name", "kapil chaudhary");

        return "test";
    }

}
