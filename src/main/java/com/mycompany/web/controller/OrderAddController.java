package com.mycompany.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.dao.interfaces.CityDao;
import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.dao.interfaces.OrderDao;
import com.mycompany.db.embeddableClasses.Address;
import com.mycompany.db.entity.Book;
import com.mycompany.db.entity.BookOrder;
import com.mycompany.db.entity.Order;
import com.mycompany.db.enumClasses.Status;
import com.mycompany.web.classes.BookOrderInfo;
import com.mycompany.web.classes.OrderEditInfo;
import com.mycompany.web.validator.OrderEditInfoValidator;

@Controller
@RequestMapping("/orderAdd")
@SessionAttributes(names= {"cities", "statuses", "clients"})
public class OrderAddController {

	@Autowired
	CityDao cityDao;

	@Autowired
	ClientDao clientDao;

	@Autowired
	BookDao bookDao;
	
	@Autowired
	OrderDao orderDao;

	@GetMapping
	public String orderAddGet(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		OrderEditInfo orderEditInfo = new OrderEditInfo();
		List<BookOrderInfo> listBookOrderInfo = new ArrayList<>();
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		// если на страницу было сделано перенаправление => получаем значения атрибутов и заполняем промеж. объект
		if (flashMap != null) {
			Order order = (Order) flashMap.get("redirectOrder");
			
			System.out.println(order);
			orderEditInfo.setAddress(order.getAddress().getAddress());
			orderEditInfo.setZipCode(Integer.valueOf(order.getAddress().getZipCode()).toString());
			orderEditInfo.setCity(order.getCity());
			orderEditInfo.setClient(order.getClient());
			
			for (BookOrder bo : order.getBookOrders()) {
				BookOrderInfo bookOrderInfo = new BookOrderInfo();
				bookOrderInfo.setBook(bo.getBook());
				bookOrderInfo.setOldNumber(bo.getNumber());
				listBookOrderInfo.add(bookOrderInfo);
			}
		}else {
		
			// добавляем книги из корзины в промежуточный объект
		
		for (Book book : bookDao.getSelectedBooks()) {
			BookOrderInfo boi = new BookOrderInfo();
			boi.setBook(book);
			boi.setOldNumber(book.getNumOfSelected());
			listBookOrderInfo.add(boi);
		}
		}

		orderEditInfo.setBookOrders(listBookOrderInfo);

		model.addAttribute("orderEditInfo", orderEditInfo);
		new InitFormOrderData(cityDao, clientDao).initModel(model);
		return "orderAdd";
	}

	@PostMapping
	public String orderAddPost(@Valid @ModelAttribute("orderEditInfo") OrderEditInfo orderEditInfo,
			BindingResult errors, Model model, SessionStatus sessionStatus) {

		// введены ли данные в форму корректно?
		new OrderEditInfoValidator(bookDao).validate(orderEditInfo, errors);
		
		if (orderEditInfo.getBookOrders().isEmpty()) {
			errors.reject("OrderAddController.TrashIsEmpty");
		}

		if (errors.hasErrors()) {
			return "orderAdd";
		}
		
		// формирование нового заказа
		Order order = new Order();
		
		order.setAddress(new Address(orderEditInfo.getAddress(), Integer.valueOf(orderEditInfo.getZipCode())));
		order.setCity(orderEditInfo.getCity());
		order.setClient(orderEditInfo.getClient());
		order.setStatus(Status.PROCESSING);
		
		List<BookOrder> listBookOrder = new ArrayList<>();
		
		for(BookOrderInfo boi : orderEditInfo.getBookOrders()) {
			Book book = bookDao.find(boi.getBook().getId());
			
			BookOrder bookOrder = new BookOrder();
			bookOrder.setBook(book);
			bookOrder.setNumber(Integer.valueOf(boi.getCurrentNumber()));
			bookOrder.setOrder(order);
			listBookOrder.add(bookOrder);
			
			// изменяем значение количество в Book
			book.setQuantity(book.getQuantity() - Integer.valueOf(boi.getCurrentNumber()));
			// удаляем книгу из корзины
			book.setSelected(false);
			bookDao.update(book);
		}
		
		order.setBookOrders(listBookOrder);
		
		// добавление заказа и каскадно добавление BookOrder
		orderDao.add(order);
		
		// очистка модели
		sessionStatus.setComplete();
		model.asMap().clear();

		return "redirect:/orderInfo/" + order.getId();
	}

	/**
	 * Класс инициализации формы в представлении orderAdd значениями
	 */
	private class InitFormOrderData {
		CityDao cityDao;
		ClientDao clientDao;

		public InitFormOrderData(CityDao cityDao, ClientDao clientDao) {
			this.cityDao = cityDao;
			this.clientDao = clientDao;
		}

		public void initModel(Model model) {

			// тип Status в String будет преобразован стандартным конвертером Enum-ов
			model.addAttribute("statuses", Status.values());

			// используем ClientToString Converter
			model.addAttribute("cities", cityDao.list());

			model.addAttribute("clients", clientDao.list().stream().map(
					client -> client.getFirstName() + " " + client.getLastName() + " " + client.getMobileTelephone())
					.collect(Collectors.toList()));
		}
	}

}
