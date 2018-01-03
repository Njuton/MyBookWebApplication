package com.mycompany.dao.classes;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.GroupDao;
import com.mycompany.db.entity.security.Group;

/**
 * Реализация Dao-интерфейса {@link com.mycompany.dao.interfaces.GroupDao}
 */
@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class GroupDaoImpl extends PersistenceDao<Group, Long> implements GroupDao {

	@Override
	public Group getGroupByName(String name) {
		
		Query query = em.createQuery("SELECT g FROM Group g WHERE g.groupName='" + name + "'");

		Group group = null;
		try {
			group = (Group) query.getSingleResult();
		} catch (NoResultException exc) {
		}
		return group;
	}
	
}
