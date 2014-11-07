package com.cl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="clfriend_relation")
public class UserFriends {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(optional=false)
	@JoinColumn(name="id")
	private User user;
	
	@OneToOne(optional=false)
	@JoinColumn(name="id")
	private User friend;

	@Column(name="are_friends")
	private String isFriend;
}

