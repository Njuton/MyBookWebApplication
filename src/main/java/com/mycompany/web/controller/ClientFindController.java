package com.mycompany.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.db.entity.Client;

/**
 * Контроллер для отображения информации о имющихся клиентах/клиенте
 */
@Controller
@RequestMapping("/clientFind")
public class ClientFindController {
	
	@Autowired
	ClientDao clientDao;
	
	@GetMapping
	public String clientFindGet(Model model) {
		List<Client> clients = clientDao.list();
		
		if (clients.size() > 1 || clients.isEmpty()) {
			model.addAttribute("clients", clients);
			return "clients";
		}
		
		return "redirect:/clientInfo/" + clients.get(0).getId();
	}

}
