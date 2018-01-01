package com.mycompany.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.dao.interfaces.CityDao;
import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.db.embeddableClasses.Address;
import com.mycompany.db.entity.Client;
import com.mycompany.web.classes.ClientEditInfo;
import com.mycompany.web.validator.ClientEditInfoValidator;

/**
 * Контроллер предоставляет возможность добавления новых клиентов в БД.
 */
@Controller
@RequestMapping("/clientAdd")
@SessionAttributes(names = { "cities" })
public class ClientAddController {

	@Autowired
	CityDao cityDao;

	@Autowired
	ClientDao clientDao;

	// clientEditInfo создастся и добавиться в модель автоматически 
	
	@GetMapping
	public String clientAddGet(Model model, ClientEditInfo clientEditInfo) {
		new InitFormClientData(cityDao).initModel(model);
		return "clientEdit";
	}

	@PostMapping
	public String clientAddPost(@Valid @ModelAttribute("clientEditInfo") ClientEditInfo cei,
			BindingResult bindingResult, Model model) {

		// проверка на ошибки при вводе данных в форму
		
		if (bindingResult.hasErrors())
			return "clientEdit";

		// возможно этот клиент уже существует?
		
		new ClientEditInfoValidator(clientDao).validate(cei, bindingResult);
		
		if (bindingResult.hasErrors())
			return "clientEdit";

		// если клиент действительно новый, создаем объект client и заполняем значениями 
		
		Client newClient = new Client();
		
		newClient.setFirstName(cei.getFirstName());
		newClient.setLastName(cei.getLastName());
		newClient.setEmail(cei.getEmail());
		newClient.setMobileTelephone(cei.getMobileTelephone());
		newClient.setAddress(new Address(cei.getAddress(), Integer.valueOf(cei.getZipCode())));
		newClient.setCity(cei.getCity());
		newClient.setMobileTelephone(cei.getMobileTelephone());

		clientDao.add(newClient);

		return "redirect:/clientInfo/" + newClient.getId();
	}
	
	/**
	 * Инициализация компонентов формы начальными значениями
	 */
	private class InitFormClientData {

		CityDao cityDao;

		public InitFormClientData(CityDao cityDao) {
			this.cityDao = cityDao;
		}

		public void initModel(Model model) {
			
			// добавление городов в модель
			
			model.addAttribute("cities", cityDao.list());
		}
	}
}
