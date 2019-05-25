package com.respondeaqui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	@GetMapping("/register")
    public String register() {
        return "register";
    }
	
	@GetMapping("/fragments/bannerInitial")
    public String bannerInitial() {
        return "bannerInitial";
    }
	
	@GetMapping("/timeline")
    public String timeline() {
        return "timeline";
    }
	
	@GetMapping("/fragments/sidebar")
    public String sidebar() {
        return "sidebar";
    }
	
	@GetMapping("/fragments/navbar")
    public String navbar() {
        return "navbar";
    }
	
	@GetMapping("/fragments/post")
    public String post() {
        return "post";
    }
	
	@GetMapping("/newForm")
    public String newForm() {
        return "newForm";
    }
}
