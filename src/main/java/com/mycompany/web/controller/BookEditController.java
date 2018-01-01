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
import org.springframework.web.bind.annotation.PathVariable;
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

/**
 * Контроллер для редактирования информации о книге и сохранении изменений в БД.
 */
@Controller
@RequestMapping("/bookEdit")
@SessionAttributes(names = { "genres", "paperTypes", "authors", "publishingHouses", "years" })
public class BookEditController {

	@Autowired
	BookDao bookDao;

	@Autowired
	AuthorDao authorDao;

	@Autowired
	PublishingHouseDao publishingHouseDao;

	@Autowired
	StringToAuthorConverter stringToAuthorConverter;

	@GetMapping("/{bookId}")
	public String editBookGet(@PathVariable("bookId") int bookId, Model model) {

		Book book = bookDao.find(bookId);

		/* инициализация промежуточного объекта для обмена данными между контроллером и
		   представлением
		*/

		BookEditInfo bookEditInfo = new BookEditInfo();
		bookEditInfo.setGenre(book.getGenre());
		bookEditInfo.setPaperType(book.getPaperType());
		bookEditInfo.setNumOfPages(book.getNumOfPages().toString());
		bookEditInfo.setPrice(Integer.valueOf(book.getPrice()).toString());
		bookEditInfo.setQuantity(Integer.valueOf(book.getQuantity()).toString());
		bookEditInfo.setTitle(book.getTitle());
		bookEditInfo.setYear(book.getYear());
		bookEditInfo.setPublishingHouse(book.getPublishingHouse());

		List<Author> lb = new ArrayList<>(book.getAuthors());
		
		// инициализация полей author1, author2, author3
		
		switch (lb.size()) {
		case 0:
			break;
		case 1:
			bookEditInfo.setAuthor1(lb.get(0).getFirstName() + " " + lb.get(0).getLastName());
			break;
		case 2:
			bookEditInfo.setAuthor1(lb.get(0).getFirstName() + " " + lb.get(0).getLastName());
			bookEditInfo.setAuthor2(lb.get(1));
			break;
		default:
			bookEditInfo.setAuthor1(lb.get(0).getFirstName() + " " + lb.get(0).getLastName());
			bookEditInfo.setAuthor2(lb.get(1));
			bookEditInfo.setAuthor3(lb.get(2));
		}
		
		// добавление данных в модель
		
		model.addAttribute("bookEditInfo", bookEditInfo);
		new InitFormBookData(authorDao, publishingHouseDao).initModel(model);

		return "bookEdit";
	}

	@PostMapping("/{bookId}")
	public String editBookPost(@PathVariable("bookId") int bookId,
			@Valid @ModelAttribute("bookEditInfo") BookEditInfo bei, BindingResult bindingResult,
			SessionStatus sessionStatus, Model model) {

		if (bindingResult.hasErrors())
			return "bookEdit";

		// приводим значения String, валидировавшиеся ранее, к необходимому типу

		Integer numOfPages = Integer.valueOf(bei.getNumOfPages());
		int price = Integer.valueOf(bei.getPrice());
		int quantity = Integer.valueOf(bei.getQuantity());
		Author author1 = stringToAuthorConverter.convert(bei.getAuthor1());

		Book oldBook = bookDao.find(bookId);

		// составляем список выбранных авторов
		
		List<Author> newAuthors = (Arrays.asList(author1, bei.getAuthor2(), bei.getAuthor3()).stream()
				.filter(author -> author != null).collect(Collectors.toList()));

		// поскольку владелец ассоциации @ManyToMany сущность Author => удалять книги можно только через неё
		
		for (Author a : oldBook.getAuthors()) {
			if (!newAuthors.contains(a)) {
				a.getBooks().remove(oldBook);
				authorDao.update(a);
			}
		}

		// заполняем существующую книгу новыми значениями
		
		oldBook.setTitle(bei.getTitle());
		oldBook.setGenre(bei.getGenre());
		oldBook.setNumOfPages(numOfPages);
		oldBook.setPrice(price);
		oldBook.setQuantity(quantity);
		oldBook.setPaperType(bei.getPaperType());
		oldBook.setPublishingHouse(bei.getPublishingHouse());
		oldBook.setYear(bei.getYear());
		oldBook.setAuthors(new ArrayList<>(newAuthors));
		
		// сохраняем изменения в БД
		
		bookDao.update(oldBook);

		sessionStatus.setComplete();
		model.asMap().clear();

		return "redirect:/bookInfo/" + oldBook.getId();
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
			
			// передаем жанры в модель
			
			List<String> genres = new ArrayList<>();
			for (Genre g : Genre.values())
				genres.add(g.toString());

			// передаем типы бумаги в модель
			
			String[] paperTypes = new String[] { PaperType.OFFSET.toString(), PaperType.NEWSPAPER.toString() };
			
			// передаем издательства в модель
			
			List<String> publishingHouses = new ArrayList<>();
			publishingHouseDao.list().stream().map(x -> x.getName())
					.collect(Collectors.toCollection(() -> publishingHouses));
			
			// передаем список годов выпуска книг в модель
			
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
