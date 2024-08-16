package org.esfe.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller

@RequestMapping("/")
public class Homecontrollers {
    @GetMapping
    public String index() {
        return "Home/index";
    }
    @GetMapping("/formnotes")
    public String formnotes() {
        return "Home/formnotes";
    }
    @GetMapping("/advancedsearch")
    public String advancedsearch() {
        return "Home/advancedsearch";
    }
    @GetMapping("/setreminder")
    public String setreminder() {
        return "Home/setreminder";
    }

}




