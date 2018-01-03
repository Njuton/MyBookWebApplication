package com.mycompany.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
public class LoginController {
	
	/**
	 * @param error - передается SpringSecurity в случае ошибки аутентификации
	 */
	@GetMapping
	public void loginGet(@RequestParam(value = "error", required = false) String error, Model model) {
		
		if (error != null) {
			model.addAttribute("error", "Неверное имя пользователя или пароль.");
		}
	}
	
	

}
