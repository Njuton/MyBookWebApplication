package com.mycompany.dao.classes;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.UserDao;
import com.mycompany.db.entity.security.User;

/**
 * Реализация DAO-интерфейса {@link com.mycompany.dao.interfaces.UserDao}
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserDaoImpl extends PersistenceDao<User, String> implements UserDao {

	@Override
	public User getUserByName(String name) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username='" + name + "'");
		User user = null;
		
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException exc) {
		}
		
		return user;
	}
}
