package com.mycompany.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.db.entity.Client;

/**
 * Контроллер для отображения всей информации о заданном клиенте {@link #clientInfoGet(int, Model)} и удалении клиента
 * из БД - {@link #clientInfoPost(int)}
 */
@Controller
@RequestMapping(value="/clientInfo")
public class ClientInfoController {
	
	@Autowired
	ClientDao clientDao;
	
	@GetMapping("/{clientId}")
	public String clientInfoGet(@PathVariable("clientId") int clientId, Model model) {
		Client client = clientDao.find(clientId);
		model.addAttribute(client);
		return "clientInfo";
	}
	
	@PostMapping(value="/{clientId}")
	public String clientInfoPost(@PathVariable("clientId") int clientId) {
		Client client = clientDao.find(clientId);
		clientDao.remove(client);
		return "redirect:/clientFind";
	}
}
