package com.mycompany.dao.classes;

import org.springframework.stereotype.Repository;

import com.mycompany.dao.interfaces.BookOrderDao;
import com.mycompany.db.entity.BookOrder;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.BookOrderDao}
 */
@Repository
public class BookOrderDaoImpl extends PersistenceDao<BookOrder, Integer> implements BookOrderDao{

}
