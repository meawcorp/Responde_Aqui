package com.respondeaqui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
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
}
