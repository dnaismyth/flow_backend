package dto;

import java.util.Date;


public class Notification {
	
	private Long userId;
	
	private NotificationType notifyType;
	
	private Date dateCreated;
	
	public Notification(Long userId, NotificationType notifyType){
		this.userId = userId;
		this.notifyType = notifyType;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setNotifyType(NotificationType notifyType){
		this.notifyType = notifyType;
	}
	
	public NotificationType getNotifyType(){
		return notifyType;
	}
	
	public void setCreatedDate(Date dateCreated){
		this.dateCreated = dateCreated;
	}
	
	public Date getCreatedDate(){
		return dateCreated;
	}
	
	
}
