package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import dto.NotificationType;

@Entity
@Table(name="notification")
public class RNotification extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6882898685738243753L;

	/**
	 * Used to store the notification recipient
	 */
	@Column(name="user_id", nullable = false)
	private Long userId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationType notifyType;
	
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
	
}
