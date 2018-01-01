package com.mycompany.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.dao.interfaces.AuthorDao;
import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.dao.interfaces.PublishingHouseDao;
import com.mycompany.db.entity.Book;
import com.mycompany.db.enumClasses.Genre;
import com.mycompany.db.enumClasses.PaperType;
import com.mycompany.web.classes.BookFindInfo;
import com.mycompany.web.editor.StringToIntegerEditor;

/**
 * Контроллер для поиска книги по различным параметрам
 */
@Controller
@RequestMapping("/bookFind")
@SessionAttributes(names = { "genres", "publishingHouses", "authors", "years", "paperTypes" })
public class BookFindController {

	@Autowired
	PublishingHouseDao publishingHouseDao;

	@Autowired
	AuthorDao authorDao;

	@Autowired
	BookDao bookDao;

	/**
	 * Регистрируем Editor для обработки числовых годов и "не выбрано"
	 */
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new StringToIntegerEditor());
	}

	@GetMapping
	public Model bookFindGet(@ModelAttribute BookFindInfo bookFindInfo, Model model) {
		// добавляем в модель промежуточный объект и объект инициализации значениями
		// компонентов формы
		model.addAttribute(bookFindInfo);
		new InitFormBookData(authorDao, publishingHouseDao).initModel(model);
		return model;
	}

	@PostMapping
	public String bookFindPost(@Valid @ModelAttribute("bookFindInfo") BookFindInfo bfi, BindingResult br, Model model,
			SessionStatus sessionStatus) {

		// model уже содержит атрибут bfi
		if (br.hasErrors())
			return "bookFind";

		// если нет ошибок ввода

		Integer priceBegin = bfi.getPriceBegin().isEmpty() ? null : Integer.valueOf(bfi.getPriceBegin());
		Integer priceEnd = bfi.getPriceEnd().isEmpty() ? null : Integer.valueOf(bfi.getPriceEnd());

		// запрос к БД на поиск книг по параметрам
		List<Book> listOfBook = bookDao.getBooksByParam(bfi.getTitle(), bfi.getAuthor(), bfi.getGenre(),
				bfi.getPublishingHouse(), bfi.getPaperType(), bfi.getYearBegin(), bfi.getYearEnd(), priceBegin,
				priceEnd, bfi.getAvailable());

		if (listOfBook.isEmpty()) {
			/*
			 * добавляем глобальную ошибку, не связанную с полем объекта. Отображается
			 * <form:errors /> в JSP
			 */
			br.reject("BookFindController.BookNotFind");
			return "bookFind";
		} else if (listOfBook.size() > 1) {

			// удаляем объекты инициализации из сессии
			sessionStatus.setComplete();

			// сортируем список книг по фамилии, затем по имени
			Collections.sort(listOfBook, new Comparator<Book>() {

				@Override
				public int compare(Book o1, Book o2) {
					int firstRes = o1.getAuthors().get(0).getLastName().compareTo(o2.getAuthors().get(0).getLastName());

					if (firstRes != 0)
						return firstRes;
					else
						return o1.getAuthors().get(0).getFirstName().compareTo(o2.getAuthors().get(0).getFirstName());
				}
			});
			
			model.addAttribute("books", listOfBook);
			return "books";
		}

		// если книга одна => redirect на страницу отображения информации о книге
		
		sessionStatus.setComplete();
		model.asMap().clear();
		return "redirect:/bookInfo/" + listOfBook.get(0).getId();
	}

	/**
	 * Инициализация компонентов формы начальными значениями
	 */
	private class InitFormBookData {
		AuthorDao authorDao;

		PublishingHouseDao publishingHouseDao;

		public InitFormBookData(AuthorDao a, PublishingHouseDao ph) {
			authorDao = a;
			publishingHouseDao = ph;
		}

		public void initModel(Model model) {
			// передаем жанры и "не выбрано" в представление
			List<String> genres = new ArrayList<>();
			genres.add("-");
			for (Genre g : Genre.values())
				genres.add(g.toString());

			// передаем типы бумаги и "не выбрано" в представление
			String[] paperTypes = new String[] { "-", PaperType.OFFSET.toString(), PaperType.NEWSPAPER.toString() };

			// передаем издательства и "не выбрано" в представление
			List<String> publishingHouses = new ArrayList<>();
			publishingHouses.add("-");
			publishingHouseDao.list().stream().map(x -> x.getName())
					.collect(Collectors.toCollection(() -> publishingHouses));

			// передаем года и "не выбрано" в представление
			List<String> years = new ArrayList<>();
			years.add("-");
			IntStream.range(1990, 2019).mapToObj(x -> Integer.valueOf(x).toString())
					.collect(Collectors.toCollection(() -> years));

			// передаем список авторов и "не выбрано" в представление
			List<String> authors = new ArrayList<>();
			authors.add("-");
			authorDao.list().stream().map(a -> a.getFirstName() + " " + a.getLastName())
					.collect(Collectors.toCollection(() -> authors));

			model.addAttribute("genres", genres);
			model.addAttribute("paperTypes", paperTypes);
			model.addAttribute("publishingHouses", publishingHouses);
			model.addAttribute("years", years);
			model.addAttribute("authors", authors);
		}
	}
}
