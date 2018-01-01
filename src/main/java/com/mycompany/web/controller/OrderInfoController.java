package com.mycompany.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.dao.interfaces.OrderDao;
import com.mycompany.db.entity.Order;

/**
 * Контроллер позволяет отображать информацию о заказе {@link #orderInfoGet(int, Model)} 
 * и удалять заказ {@link #orderInfoPost(int, Model)}
 */
@Controller
@RequestMapping("orderInfo")
public class OrderInfoController {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ClientDao clientDao;

	@GetMapping(value = "{orderId}")
	String orderInfoGet(@PathVariable("orderId") int orderId, Model model) {
		Order order = orderDao.find(orderId);
		model.addAttribute("order", order);
		return "orderInfo";
	}

	@PostMapping
	@RequestMapping(value = "{orderId}")
	String orderInfoPost(@PathVariable("orderId") int orderId, Model model) {
		
		Order order = orderDao.find(orderId);
		orderDao.remove(order);
		
		return "redirect:/orderFind";
	}
}
