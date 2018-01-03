package com.mycompany.web.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.db.entity.Book;
import com.mycompany.web.classes.MenuBookInfo;

/**
 * Контроллер работает в случае ввода информации в поле ввода в header и нажатии на отправить
 */

@Controller
@RequestMapping("/menuBookFind")
public class MenuController {
	
	@Autowired
	BookDao bookDao;
	
	/**
	 * Если книги нашлись по названию => добавить список книг в модель, в противном случае
	 * выполнить перенаправление на страницу, откуда пришли и отправить ошибку в представление.
	 */
	@PostMapping
	public String menuFindPost(@ModelAttribute("menuBookInfo") MenuBookInfo menuBookInfo, BindingResult menuErrors, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttr) {
		model.asMap().keySet().stream().forEach(System.out::println);
		
		List<Book> books = bookDao.getBooksByTitle(menuBookInfo.getTitle());
		
		if (books.isEmpty()) {
			/*
			 * добавляем ошибку, связанную с полем title объекта menuBookInfo
			 * <form:errors path="title" /> в JSP
			 */
			
			menuErrors.reject("BookFindController.BookNotFind");
			
			/*
			 * добавляем ошибку во флэш-атрибуты
			 * автоматически объект ошибки будет добавлен в модель контроллера, обрабатывающего 
			 * redirect-get запрос
			 */
			
			redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.menuBookInfo", 
			model.asMap().get("org.springframework.validation.BindingResult.menuBookInfo"));
			
			/*
			 * Выделяем часть URI из URL-referer и делаем на нее redirect; 
			 * (redirect на страницу откуда пришли)
			 */
			
			Pattern pattern = Pattern.compile("http://localhost:8080" + request.getContextPath() + "((\\w|\\W)*)");
			Matcher matcher = pattern.matcher(request.getHeader("referer"));
			matcher.find();
			
			return "redirect:" + matcher.group(1);
			
		} else if (books.size() > 1) {

			// сортируем список книг по фамилии, затем по имени
			
			Collections.sort(books, new Comparator<Book>() {

				@Override
				public int compare(Book o1, Book o2) {
					int firstRes = o1.getAuthors().get(0).getLastName().compareTo(o2.getAuthors().get(0).getLastName());

					if (firstRes != 0)
						return firstRes;
					else
						return o1.getAuthors().get(0).getFirstName().compareTo(o2.getAuthors().get(0).getFirstName());
				}
			});
			
			model.addAttribute("books", books);
			return "books";
		}
		
		// если книга одна => redirect на страницу отображения информации о книге
		
		return "redirect:/bookInfo/" + books.get(0).getId();
	}
}
