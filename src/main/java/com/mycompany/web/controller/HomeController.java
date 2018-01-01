package com.mycompany.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Контроллер главной страницы
 */
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String welcomeHandler(Model model) {
		
		return "welcome";
	}

}
