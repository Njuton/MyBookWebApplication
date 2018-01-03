package com.mycompany.dao.classes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.interfaces.GroupMemberDao;
import com.mycompany.db.entity.security.GroupMember;

@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class GroupMemberDaoImpl extends PersistenceDao<GroupMember, Long> implements GroupMemberDao{

}
