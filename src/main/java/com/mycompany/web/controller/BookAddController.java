package com.mycompany.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

import com.mycompany.dao.interfaces.AuthorDao;
import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.dao.interfaces.PublishingHouseDao;
import com.mycompany.db.entity.Author;
import com.mycompany.db.entity.Book;
import com.mycompany.db.enumClasses.Genre;
import com.mycompany.db.enumClasses.PaperType;
import com.mycompany.web.classes.BookEditInfo;
import com.mycompany.web.converter.StringToAuthorConverter;
import com.mycompany.web.validator.BookEditInfoValidator;

/**
 * Контроллер работает при работе с вставкой новой книги.
 */
@Controller
@RequestMapping("/bookAdd")
@SessionAttributes(names = { "genres", "paperTypes", "authors", "publishingHouses", "years" })
public class BookAddController {

	@Autowired
	AuthorDao authorDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	PublishingHouseDao publishingHouseDao;

	@Autowired
	StringToAuthorConverter stringToAuthorConverter;

	@GetMapping
	public String bookAddGet(Model model) {
		BookEditInfo bei = new BookEditInfo();
		model.addAttribute("bookEditInfo", bei);
		new InitFormBookData(authorDao, publishingHouseDao).initModel(model);
		
		// используем представление как и при редактировании книги
		
		return "bookEdit";
	}

	@PostMapping
	public String bookAddPost(@Valid @ModelAttribute("bookEditInfo") BookEditInfo bei, BindingResult bindingResult,
			SessionStatus sessionStatus, Model model) {

		// проверяем на ошибки ввода в формы
		
		if (bindingResult.hasErrors())
			return "bookEdit";
		
		// приводим значения String, валидировавшиеся ранее, к необходимому типу
		
		Integer numOfPages = Integer.valueOf(bei.getNumOfPages());
		int price = Integer.valueOf(bei.getPrice());
		int quantity = Integer.valueOf(bei.getQuantity());
		Author author1 = stringToAuthorConverter.convert(bei.getAuthor1());
		
		// возможно книга, которую мы ввела, уже есть в нашей БД?
		
		new BookEditInfoValidator(bookDao, price, author1).validate(bei, bindingResult);
		
		if (bindingResult.hasErrors())
			return "bookEdit";

		// если книга действительно новая => создаем объект книги и заполняем знчениями
		
		Book newBook = new Book();


		newBook.setTitle(bei.getTitle());
		newBook.setGenre(bei.getGenre());
		newBook.setNumOfPages(numOfPages);
		newBook.setPrice(price);
		newBook.setQuantity(quantity);
		newBook.setPaperType(bei.getPaperType());
		newBook.setPublishingHouse(bei.getPublishingHouse());
		newBook.setYear(bei.getYear());
	
		bookDao.add(newBook);
		Book bookFind = bookDao.find(newBook.getId());
		
		// добавляем авторов к detach-сущности book (объекты авторов уникальны)
		
		List<Author> newAuthors = Arrays.asList(author1, bei.getAuthor2(), bei.getAuthor3()).stream()
				.filter(author -> author != null).distinct().collect(Collectors.toList());
		
		for (Author a : newAuthors) {
			a.getBooks().add(bookFind);
			authorDao.update(a);
		}

		sessionStatus.setComplete();
		model.asMap().clear();
		
		return "redirect:/bookInfo/" + newBook.getId();
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

			// передаем список жанров в модель
			
			List<String> genres = new ArrayList<>();
			for (Genre g : Genre.values())
				genres.add(g.toString());
			
			// передаем типы бумаги в модель 
			
			String[] paperTypes = new String[] { PaperType.OFFSET.toString(), PaperType.NEWSPAPER.toString() };

			// передаем список издательств в модель
			
			List<String> publishingHouses = new ArrayList<>();
			publishingHouseDao.list().stream().map(x -> x.getName())
					.collect(Collectors.toCollection(() -> publishingHouses));

			// передаем список годов в модель 
			
			List<String> years = new ArrayList<>();
			IntStream.range(1990, 2019).mapToObj(x -> Integer.valueOf(x).toString())
					.collect(Collectors.toCollection(() -> years));

			// передаем список авторов и "не выбрано" в модель
			
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
