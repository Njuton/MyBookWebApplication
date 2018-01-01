package com.mycompany.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.dao.interfaces.CityDao;
import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.db.embeddableClasses.Address;
import com.mycompany.db.entity.Client;
import com.mycompany.web.classes.ClientEditInfo;

/**
 * Контроллер предоставляет возможность редактирование клиентов и их последующее сохранение в БД
 */
@Controller
@RequestMapping("/clientEdit")
@SessionAttributes(names= {"cities"})
public class ClientEditController {
	
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	CityDao cityDao;
	
	@GetMapping(value="/{clientId}")
	public String clientEditGet(@PathVariable("clientId") int clientId, Model model) {
		
		// находим нужного клиента 
		
		Client client = clientDao.find(clientId);
		
		// инициализируем промежуточный объект значениями из объекта client
		
		ClientEditInfo cei = new ClientEditInfo();
		cei.setAddress(client.getAddress().getAddress());
		cei.setEmail(client.getEmail());
		cei.setFirstName(client.getFirstName());
		cei.setLastName(client.getLastName());
		cei.setMobileTelephone(client.getMobileTelephone());
		cei.setZipCode(Integer.valueOf(client.getAddress().getZipCode()).toString());
		cei.setCity(client.getCity());
		
		model.addAttribute("clientEditInfo", cei);
		new InitFormClientData(cityDao).initModel(model);
		
		return "clientEdit";
	}
	
	@PostMapping(value="/{clientId}")
	public String clientEditPost(@PathVariable("clientId") int clientId, 
			@Valid @ModelAttribute("clientEditInfo") ClientEditInfo cei, BindingResult bindingResult, Model model,
			SessionStatus sessionStatus) {
		
		if (bindingResult.hasErrors())
			return "clientEdit";
		
		// находим клиента до редактирования
		
		Client oldClient = clientDao.find(clientId);
		
		//заполняем его новыми данными и заносим в БД
		
		oldClient.setFirstName(cei.getFirstName());
		oldClient.setLastName(cei.getLastName());
		oldClient.setEmail(cei.getEmail());
		oldClient.setMobileTelephone(cei.getMobileTelephone());
		oldClient.setAddress(new Address(cei.getAddress(), Integer.valueOf(cei.getZipCode())));
		oldClient.setCity(cei.getCity());
		oldClient.setMobileTelephone(cei.getMobileTelephone());
		
		clientDao.update(oldClient);	
	
		model.asMap().clear();
		sessionStatus.setComplete();
		
		return "redirect:/clientInfo/" + oldClient.getId();
	}
	
	/**
	 * Инициализация компонентов формы начальными значениями
	 */
	private class InitFormClientData{
		
		CityDao cityDao;
		
		public InitFormClientData(CityDao cityDao) {
			this.cityDao = cityDao;
		}
		
		public void initModel(Model model) {
			model.addAttribute("cities", cityDao.list());
		}
	}
}
