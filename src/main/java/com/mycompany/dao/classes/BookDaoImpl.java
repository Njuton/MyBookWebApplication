package com.mycompany.dao.classes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.BookDao;
import com.mycompany.db.entity.Author;
import com.mycompany.db.entity.Book;
import com.mycompany.db.entity.PublishingHouse;
import com.mycompany.db.enumClasses.Genre;
import com.mycompany.db.enumClasses.PaperType;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.BookDao}
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class BookDaoImpl extends PersistenceDao<Book, Integer> implements BookDao {
	
	@Override
	public void add(Book book) {
		Book mergedBook = em.merge(book);
		book.setId(mergedBook.getId());
	}
	
	@Override
	public List<Book> getSelectedBooks() {
		return em.createQuery("SELECT b FROM Book b WHERE b.selected=TRUE").getResultList();
	}
	
	@Override
	public List<Book> getBooksByTitle(String title) {
		System.out.println(title);
		return em.createQuery("SELECT b FROM Book b WHERE b.title LIKE '%" + title + "%'").getResultList();
	}

	@Override
	public List<Book> getBooksByParam(String title, Author author, Genre genre, PublishingHouse publishingHouse,
			PaperType paperType, Integer yearBegin, Integer yearEnd, Integer priceBegin, Integer priceEnd,
			boolean available) {
		
		// если пользователь ничего не выбирал => возвратить все книги
		
		if (title == null && author == null && genre == null&& publishingHouse == null && paperType == null 
				&& yearBegin == null && yearEnd == null && priceBegin == null && priceEnd == null && available == false)
			return em.createQuery("SELECT b FROM Book b").getResultList();
		
		// иначе только книги, удовлетворяющие запросу

		String headSql = "SELECT DISTINCT b FROM Book b INNER JOIN b.authors a INNER JOIN b.publishingHouse p WHERE ";
		String titleSql = "";

		if (title != null) {
			titleSql = "b.title LIKE '%" + title + "%'";
		}

		String authorSql = "";
		
		if (author != null) {
			authorSql += "a.id=" + author.getId();
		}

		String genreSql = "";
		
		if (genre != null) {
			genreSql += "b.genre=" + genre.getClass().getName() + '.' + genre;
		}
		
		String publishingHouseSql = "";
		
		if (publishingHouse != null) {
			publishingHouseSql += "p.id=" + publishingHouse.getId();
		}
		
		String paperTypeSql = "";
		
		if (paperType != null) {
			paperTypeSql += "b.paperType=" + paperType.getClass().getName() + '.' + paperType;
		}
		
		String yearBeginSql = "";
		
		if (yearBegin != null)
			yearBeginSql += "b.year >= " + yearBegin;
		
		String yearEndSql = "";
		
		if (yearEnd != null)
			yearEndSql += "b.year <= " + yearEnd;
		
		String priceBeginSql = "";
		
		if (priceBegin != null)
			priceBeginSql += "b.price >= " + priceBegin;
		
		String priceEndSql = "";
		
		if (priceEnd != null)
			priceEndSql += "b.price <= " + priceEnd;
		
		String availableSql = "";
		
		if (available == true)
			availableSql = "b.quantity > 0";
		
		
		Collection<String> collectionResultQuery = Arrays.asList(titleSql, 
													             authorSql,
													             genreSql,  
													             publishingHouseSql, 
													             paperTypeSql, 
													             availableSql,
													             yearBeginSql,
													             yearEndSql,
													             priceBeginSql,
													             priceEndSql);
		
		// осталвяю != null строки и конкатенирую их по разделителю " AND "; В случае одного значения конкатенация не 
		// производится
		String resultSql = headSql + collectionResultQuery.stream()
												          .filter(str -> !str.equals(""))
												          .collect(Collectors.joining(" AND "));
		
		return em.createQuery(resultSql).getResultList();
	}

}
