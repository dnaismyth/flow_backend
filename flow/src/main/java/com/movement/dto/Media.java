package com.movement.dto;

/**
 * Media object that will hold the image posted within a workout
 * @author DN
 *
 */
public class Media {

	/**
	 * Owner of the media item
	 */
	
	private Long id;
	
	private Long owner_id;
	
	private String fileName;
	
	private String thumbnailFile;
	
	private String caption;
	
	public Media(){}
	
	public Long getOwnerId(){
		return owner_id;
	}
	
	public void setOwnerId(Long owner_id){
		this.owner_id = owner_id;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public void setCaption(String caption){
		this.caption = caption;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setThumbnail(String thumbnailFile){
		this.thumbnailFile = thumbnailFile;
	}
	
	public String getThumbnail(){
		return thumbnailFile;
	}
	
	
	
	
}
