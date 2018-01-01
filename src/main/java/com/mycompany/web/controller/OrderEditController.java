package com.mycompany.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.dao.interfaces.BookOrderDao;
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

/**
 * Отрабатывает при редактировании заказа. {@link #orderEditGet} отсылает на
 * страницу редактирования заказа, а {@link #orderEditPost()} сохраняет
 * сделанные изменения.
 */
@Controller
@RequestMapping("/orderEdit")
@SessionAttributes(names = { "cities", "clients", "statuses"})
public class OrderEditController {

	@Autowired
	CityDao cityDao;

	@Autowired
	ClientDao clientDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	BookOrderDao bookOrderDao;

	/**
	 * Отображение информации для предоставления возможности редактировать заказ
	 */
	@GetMapping(value = "{orderId}")
	public String orderEditGet(@PathVariable("orderId") int orderId, Model model) {
		Order order = orderDao.find(orderId);

		/* создание промежуточного объекта для обмена данными между контроллером и
		   представлением
		*/
		
		OrderEditInfo oei = new OrderEditInfo();

		// заполнение объекта значениями
		
		oei.setStatus(order.getStatus());
		oei.setClient(order.getClient());
		oei.setCity(order.getCity());
		oei.setZipCode(Integer.valueOf(order.getAddress().getZipCode()).toString());
		oei.setAddress(order.getAddress().getAddress()); 
		
		List<BookOrderInfo> listBookOrderInfo = new ArrayList<>();
		for (BookOrder bo : order.getBookOrders()) {
			BookOrderInfo boi = new BookOrderInfo();
			boi.setBook(bo.getBook());
			boi.setOldNumber(bo.getNumber());
			listBookOrderInfo.add(boi);
		}

		oei.setBookOrders(listBookOrderInfo);

		model.addAttribute("orderEditInfo", oei);
		new InitFormOrderData(cityDao, clientDao).initModel(model);
		
		return "orderEdit";
	}

	/**
	 * Удаление книги из заказа
	 */
	@PostMapping(value = "{orderId}/{index}", params = { "remove" })
	public String orderEditPostRemove(@PathVariable("orderId") int orderId, @PathVariable("index") int index, Model model,
			SessionStatus sessionStatus) {
		
		sessionStatus.setComplete();
		model.asMap().clear();
		
		// находим заказ
		
		Order order = orderDao.find(orderId);
		
		// увеличиваем количество экземпляров книги на складе
		
		Book book = order.getBookOrders().get(index).getBook();
		book.setQuantity(book.getQuantity() + order.getBookOrders().get(index).getNumber());
		bookDao.update(book);

		// если в заказе после удаления книги не останется товаров => удаляем заказ
		
		if (order.getBookOrders().size() == 1) {
			orderDao.remove(order);
			return "redirect:/orderFind/";
		}
		
		// иначе удаляем экземпляр класса BookOrder из заказа
		
		order.getBookOrders().remove(index);
		orderDao.update(order);

		return "redirect:/orderInfo/" + orderId;
	}

	/**
	 * Сохранение информации о заказе после редактирования
	 */
	@PostMapping(value = "{orderId}", params= {"save"})
	public String orderEditPost(@PathVariable("orderId") int orderId,
			@Valid @ModelAttribute("orderEditInfo") OrderEditInfo orderEditInfo, BindingResult errors, Model model,
			SessionStatus sessionStatus) {
		
		// введены ли данные в форму корректно?
		
		new OrderEditInfoValidator(bookDao).validate(orderEditInfo, errors);
		
		if (errors.hasErrors()) {
			return "orderEdit";
		}
		
		// находим данный заказ до редактирования
		
		Order order = orderDao.find(orderId);
		
		List<BookOrder> oldListBO = order.getBookOrders();
		List<BookOrderInfo> newListBO = orderEditInfo.getBookOrders();
		
		for (int i = 0; i < oldListBO.size(); i++) {
			
			// BookOrder до редактирования
			
			BookOrder oldCurrent = oldListBO.get(i);
			
			// BookOrder после радактирования
			
			BookOrderInfo newCurrent = newListBO.get(i);
			Book book = bookDao.find(newCurrent.getBook().getId());
			
			if (!(oldCurrent.getNumber() == Integer.valueOf(newCurrent.getCurrentNumber()))) {
				
				// изменяем значение количества в bookOrder
				
				oldCurrent.setNumber(Integer.valueOf(newCurrent.getCurrentNumber()));
				bookOrderDao.update(oldCurrent);
				
				//  изменяем количество товара на складе исходя из нового значения
				
				book.setQuantity(book.getQuantity() + newCurrent.getOldNumber()
					- Integer.valueOf(newCurrent.getCurrentNumber()));
			}
			
			
			// если статус заказа CANCELLED => кладем все взятые книги на склад
			
			if (orderEditInfo.getStatus() == Status.CANCELLED) {
				book.setQuantity(book.getQuantity() + Integer.valueOf(newCurrent.getCurrentNumber()));
			}
			
			bookDao.update(book);
		}

		order.setAddress(new Address(orderEditInfo.getAddress(), Integer.valueOf(orderEditInfo.getZipCode())));
		order.setCity(orderEditInfo.getCity());
		order.setClient(orderEditInfo.getClient());
		order.setStatus(orderEditInfo.getStatus());

		orderDao.update(order);

		sessionStatus.setComplete();
		model.asMap().clear();

		return "redirect:/orderInfo/" + orderId;
	}
	
	/**
	 * Работает при изменении статуса заказа в случае, если статус заказа был CANCELLED или SHIPPED
	 * @param sendStatus - отправленный представлением новый статус заказа
	 */
	@PostMapping(value="{orderId}", params={"sendStatus"})
	public String orderEditCancelledPost(@PathVariable("orderId") int orderId, 
			@RequestParam("status") String sendStatus, Model model, SessionStatus sessionStatus, 
			RedirectAttributes redirectAttributes){
		
		sessionStatus.setComplete();
		
		Order order = orderDao.find(orderId);
		
		// если заказ не изменяет свой статус => ничего не делаем
		
		if (order.getStatus() == Status.valueOf(sendStatus))
			return "redirect:/orderEdit/" + orderId;
		else
			
			// если заказ был SHIPPED, а стал PROCESSING => меняем статус и редирект на страницу инфы о заказе
			
			if (order.getStatus() == Status.SHIPPED && Status.valueOf(sendStatus) == Status.PROCESSING) {
				order.setStatus(Status.valueOf(sendStatus));
				orderDao.update(order);
				return "redirect:/orderInfo/" + orderId;
			} 
			else
				
			/* если заказ был SHIPPED, а стал CANCELLED => возвращаем книги на склад, 
			 * меняем статус и редирект на страницу инфы о заказе
			 */
	
				if (order.getStatus() == Status.SHIPPED && Status.valueOf(sendStatus) == Status.CANCELLED) {
					for (BookOrder bo : order.getBookOrders()) {
						Book book = bo.getBook();
						book.setQuantity(bo.getNumber() + book.getQuantity());
						bookDao.update(book);
					}
					order.setStatus(Status.CANCELLED);
					orderDao.update(order);
					return "redirect:/orderInfo/" + orderId;
				}
		
		/* если заказ был CANCELLED, а стал SHIPPED или PROCESSING => добавляем в редирект-модель заказ, 
		 * удаляем заказ, перенаправление на оформление нового заказа
		 */
		
		redirectAttributes.addFlashAttribute("redirectOrder", order);
		orderDao.remove(order);
		
		return "redirect:/orderAdd";
	}

	/**
	 * Класс инициализации формы в представлении orderEdit значениями
	 */
	private class InitFormOrderData {
		CityDao cityDao;
		ClientDao clientDao;

		public InitFormOrderData(CityDao cityDao, ClientDao clientDao) {
			this.cityDao = cityDao;
			this.clientDao = clientDao;
		}

		public void initModel(Model model) {
			
			// передаем в модель статусы
			
			model.addAttribute("statuses", Status.values());

			// передаем в модель города
			
			model.addAttribute("cities", cityDao.list());

			// передаем в модель клиентов
			
			model.addAttribute("clients", clientDao.list().stream().map(
					client -> client.getFirstName() + " " + client.getLastName() + " " + client.getMobileTelephone())
					.collect(Collectors.toList()));
		}
	}
}
