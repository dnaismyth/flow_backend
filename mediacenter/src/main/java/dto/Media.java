package dto;

public class Media {

	/**
	 * Owner of the media item
	 */
	private User owner;
	
	private String caption;
	
	public Media(){}
	
	public User getOwner(){
		return owner;
	}
	
	public void setOwner(User owner){
		this.owner = owner;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public void setCaption(String caption){
		this.caption = caption;
	}
	
	
	
	
}
