package com.mycompany.dao.classes;

import org.springframework.stereotype.Repository;

import com.mycompany.dao.interfaces.OrderDao;
import com.mycompany.db.entity.Book;
import com.mycompany.db.entity.Order;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.OrderDao}
 */
@Repository
public class OrderDaoImpl extends PersistenceDao<Order, Integer> implements OrderDao{
	
	@Override
	public void add(Order order) {
		Order mergedBook = em.merge(order);
		order.setId(mergedBook.getId());
	}

}
