package com.mycompany.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.dao.interfaces.OrderDao;
import com.mycompany.db.entity.Order;

/**
 * Контроллер для отображения информации обо всех заказах.
 */
@Controller
@RequestMapping("/orderFind")
public class OrderFindController {
	
	@Autowired
	OrderDao orderDao;
	
	@GetMapping
	public String orderFindGet(Model model) {
		
		List<Order> orders = orderDao.list();
		model.addAttribute("orders", orders);
		return "orders";
		
	}
}
