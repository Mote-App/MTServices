package com.cl.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="clpost_tags")
public class PostTags {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(optional=false)
	@JoinColumn(name="id")
	private Post post;
	
	@OneToOne(optional=false)
	@JoinColumn(name="id")
	private Tag tag;
	
}
