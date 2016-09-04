package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="media")
public class RMedia extends BaseEntity {

	private static final long serialVersionUID = -263193037291084187L;
	
	/**
	 * Uploaded media owner
	 */
	@Column(name="owner_id", nullable = false)
	private Long owner_id;
	
	/**
	 * Name of the file
	 */
	@Column(name="filename", nullable = false)
	private String fileName;
	
	/**
	 * The caption that will be shown alongside the media/image when it is shown
	 */
	@Column(name="caption", nullable = false)
	private String caption;
	
	@OneToOne
	private RWorkout workout;
	
	public void setOwnerId(Long owner_id){
		this.owner_id = owner_id;
	}
	
	public Long getOwnerId(){
		return owner_id;
	}
	
	public void setCaption(String caption){
		this.caption = caption;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
}
