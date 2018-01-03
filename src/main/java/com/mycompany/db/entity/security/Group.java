package com.mycompany.db.entity.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Сущность - группы для ролей
 */

@Entity
@Table(name="groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name="group_name", length=50, nullable=false)
	String groupName;
	
	@OneToMany(mappedBy="group")
	List<GroupAuthority> groupAuthorities;
	
	@OneToMany(mappedBy="group", cascade= {CascadeType.PERSIST}, fetch=FetchType.EAGER, orphanRemoval=true)
	List<GroupMember> groupMembers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<GroupAuthority> getGroupAuthorities() {
		if (groupAuthorities == null)
			groupAuthorities = new ArrayList<GroupAuthority>();
		return groupAuthorities;
	}

	public void setGroupAuthorities(List<GroupAuthority> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
		
		for (GroupAuthority ga : groupAuthorities) {
			ga.setGroup(this);
		}
	}

	public List<GroupMember> getGroupMembers() {
		if (groupMembers == null)
			groupMembers = new ArrayList<GroupMember>();
		return groupMembers;
	}

	public void setGroupMembers(List<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
		
		for (GroupMember gm : groupMembers) {
			gm.setGroup(this);
		}
	}
	
	public void addGroupMember(GroupMember groupMember) {
		getGroupMembers().add(groupMember);
		groupMember.setGroup(this);
	}
}
