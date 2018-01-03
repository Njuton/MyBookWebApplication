package com.mycompany.dao.classes;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.ClientDao;
import com.mycompany.db.entity.Client;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.ClientDao}
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class ClientDaoImpl extends PersistenceDao<Client, Integer> implements ClientDao{

	@Override
	public Client getClientByParams(String firstName, String lastName, String mobileTelephone) {
		
		String sql = "SELECT c FROM Client c WHERE c.firstName=:firstName AND "
				+ "c.lastName=:lastName AND c.mobileTelephone=:mobileTelephone";
		
		Query query = em.createQuery(sql);
		
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.setParameter("mobileTelephone", mobileTelephone);
		
		Client client = null;
		try {
			client = (Client) query.getSingleResult();
		}
		catch(NoResultException exc) {
		}
		return client;
	}
	
}
