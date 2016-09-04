package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Stores journal entries from a user to post
 *
 */
@Entity
@Table(name="journal")
public class RJournal extends BaseEntity {
	
	private static final long serialVersionUID = 877879274164789589L;
	
	@ManyToOne
	private RUser owner;
	
	@Column(name="entry", nullable=false)
	private String entry;
	
	@Column(name="title", nullable=false)
	private String title;
	
	public void setEntry(String entry){
		this.entry = entry;
	}
	
	public String getEntry(){
		return entry;
	}
	
	public RUser getOwner(){
		return owner;
	}
	
	public void setOwner(RUser owner){
		this.owner = owner;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
}
