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
	 * Name of the larger image file (shown on main feed)
	 */
	@Column(name="feedFile", nullable = false)
	private String feedFile;
	
	
	/**
	 * Name of the thumnail file (shown on user profile page)
	 */
	@Column(name="thumbnail_file", nullable = false)
	private String thumbNail;
	/**
	 * The caption that will be shown alongside the media/image when it is shown
	 */
	@Column(name="caption", nullable = false)
	private String caption;
	
	@OneToOne
	private RWorkout workout;
	
	public RMedia(){}
	
	public RMedia(String thumbNail, String feedFile){
		this.thumbNail = thumbNail;
		this.feedFile = feedFile;
	}
	
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
	
	public String getFeedFile(){
		return feedFile;
	}
	
	public void setFeedFile(String feedFile){
		this.feedFile = feedFile;
	}
	
	public String getThumbnail(){
		return thumbNail;
	}
	
	public void setThumbnail(String thumbNail){
		this.thumbNail = thumbNail;
	}
}
