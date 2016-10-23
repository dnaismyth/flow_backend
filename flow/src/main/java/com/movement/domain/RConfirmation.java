package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entity to store the status of a user's confirmation 
 * (whether or not a user has verified their e-mail address)
 * @author DN
 *
 */
@Table(name="confirmation")
@Entity
public class RConfirmation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2983853993986564076L;
	
	/**
	 * User Id
	 */
	@Column(name="user_id")
	private Long userId;
	
	/**
	 * Confirmation key (this will be generated for a user)
	 */
	@Size(min = 0, max = 100)
	@Column(name="confirmation_key")
	private String confirmationKey;
	
	/**
	 * Flag to store the status of user confirmation.
	 * True: the user has confirmed their e-mail address.
	 * False: the user has yet to confirm their e-mail address.
	 */
	private boolean confirmed;
	
	public RConfirmation(){}
	
	public RConfirmation(Long userId, String confirmationKey, boolean confirmed){
		this.userId = userId;
		this.confirmationKey = confirmationKey;
		this.confirmed = confirmed;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public String getKey(){
		return confirmationKey;
	}
	
	public void setKey(String confirmationKey){
		this.confirmationKey = confirmationKey;
	}
	
	public boolean isConfirmed(){
		return confirmed;
	}
	
	public void setConfirmed(boolean confirmed){
		this.confirmed = confirmed;
	}
	
	
}
