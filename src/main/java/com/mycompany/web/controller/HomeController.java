package com.mycompany.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Контроллер главной страницы
 */
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String welcomeHandler(Model model) {
		
		return "welcome";
	}
	
	@GetMapping("/help")
	void helpGet() {
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public void accessDenied(Principal user, Model model) {
		model.addAttribute("username", user.getName());
	}
	
}
