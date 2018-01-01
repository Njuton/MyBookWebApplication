package com.mycompany.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.db.entity.Book;
import com.mycompany.web.classes.BookOrderInfo;
import com.mycompany.web.classes.TrashInfo;
import com.mycompany.web.validator.TrashValidator;

/**
 * Контроллер для работы с корзиной
 */
@Controller
@RequestMapping("/trash")
public class TrashController {

	@Autowired
	BookDao bookDao;

	@GetMapping
	public void trashGet(Model model) {

		List<Book> listOfBooks = bookDao.getSelectedBooks();
		List<BookOrderInfo> listBookOrderInfo = new ArrayList<>();

		/*
		 * создать промежуточный объект для обмена информацией между контроллером и
		 * представлением и внести в него данные
		 */
		TrashInfo trashInfo = new TrashInfo();

		for (Book book : listOfBooks) {
			BookOrderInfo boi = new BookOrderInfo();
			boi.setBook(book);
			boi.setOldNumber(book.getNumOfSelected());
			listBookOrderInfo.add(boi);
		}

		trashInfo.setListBookOrderInfo(listBookOrderInfo);

		model.addAttribute("trashInfo", trashInfo);
	}

	/**
	 * Вызывается при попытке очистить корзину
	 */
	@PostMapping(params = { "clearTrash" })
	public String trashClearPost(@ModelAttribute("trashInfo") TrashInfo trashInfo) {

		// если корзина пуста => ничего не делаем

		if (trashInfo.getListBookOrderInfo().isEmpty())
			return "trash";

		
		for (BookOrderInfo boi : trashInfo.getListBookOrderInfo()) {
			Book book = bookDao.find(boi.getBook().getId());
			book.setSelected(false);
			bookDao.update(book);
		}

		return "redirect:/trash";
	}
	
	/**
	 * Вызывается при попытке сохранить изменения о количестве книг
	 */
	@PostMapping(params = { "save" })
	public String trashSavePost(@ModelAttribute("trashInfo") TrashInfo trashInfo, BindingResult errors) {
		
		if (trashInfo.getListBookOrderInfo().isEmpty())
			return "trash";

		// если введенные числа ошибочны
		
		new TrashValidator(bookDao).validate(trashInfo, errors);

		if (errors.hasErrors()) {
			return "trash";
		}
		
		// иначе изменяем значение numOfSelected
		
		for (BookOrderInfo boi : trashInfo.getListBookOrderInfo()) {
			Book book = bookDao.find(boi.getBook().getId());
			book.setNumOfSelected(Integer.valueOf(boi.getCurrentNumber()));
			bookDao.update(book);
		}

		return "redirect:/trash";
	}

}
