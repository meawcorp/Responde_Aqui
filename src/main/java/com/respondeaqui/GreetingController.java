package com.respondeaqui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/form")
    public String form(Model model) {
		model.addAttribute("module", "timeline");
        return "form";
    }
	
	@GetMapping("/answeredForms")
    public String answeredForms(Model model) {
		model.addAttribute("module", "answeredForms");
        return "answeredForms";
    }
	
	@GetMapping("/myForms")
    public String myForms(Model model) {
		model.addAttribute("module", "myForms");
        return "myForms";
    }
	
	@GetMapping("/editForm")
    public String editForm(Model model) {
		model.addAttribute("module", "myForms");
        return "editForm";
    }
}
