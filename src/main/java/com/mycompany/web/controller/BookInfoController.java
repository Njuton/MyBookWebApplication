package com.mycompany.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.dao.interfaces.AuthorDao;
import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.db.entity.Author;
import com.mycompany.db.entity.Book;

/**
 * Контроллер для получения информации о книге {@link #bookInfoGet}, 
 * удалении книги {@link #bookInfoPost} или добавлении книги в корзину
 * {@link #bookInfoPost2}
 */
@Controller
@RequestMapping("/bookInfo")
public class BookInfoController {
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	AuthorDao authorDao;
	
	@GetMapping("/{bookId}")
	public String bookInfoGet(@PathVariable("bookId") int bookId, Model model) {
		model.addAttribute("book", bookDao.find(bookId));
		return "bookInfo";
	}
	
	// если запрос содержит параметр remove => отрабатывает данный метод
	
	@PostMapping(value="/{bookId}",  params={"remove"})
	public String bookInfoPost(@PathVariable("bookId") int bookId,  Model model) {
		Book book = bookDao.find(bookId);
		
		/* bookDao.remove(book) не будет работать для ассоциации ManyToMany с главной сущностью Author,
		 * поскольку это приведет к удалению book из Book, но не из Author_Book (ссылочная целостность внешнго ключа) 
		 */
		
		for (Author a : book.getAuthors()) {
			if (a.getBooks().contains(book)) {
				a.getBooks().remove(book);
				authorDao.update(a);
			}
		}
		
		bookDao.remove(book);
		
		return "redirect:/bookFind";
	}
	
	@PostMapping(value="/{bookId}", params={"addToTrash"})
	public String bookInfoPost2(@PathVariable("bookId") int bookId, @RequestParam("addToTrash") boolean selected, Model model) {
		Book book = bookDao.find(bookId);
		
		if (selected == true) {
			model.addAttribute("error","Книга уже находится в корзине");
			model.addAttribute("book", book);
			return "bookInfo";
		}
		
		book.setSelected(true);
		book.setNumOfSelected(1);
		bookDao.update(book);	
		
		return "redirect:/bookInfo/"+bookId;
	}
}
