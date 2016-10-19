package com.movement.controller.dto;

/**
 * Used in password reset request.
 * User will provide their email.
 * @author DN
 *
 */
public class ResetRequest {
	
	private String email;
	
	public ResetRequest(String email){
		this.email = email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
}
