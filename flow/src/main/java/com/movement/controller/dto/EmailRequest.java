package com.movement.controller.dto;

/**
 * For a user to provide their e-mail address.
 * Example use case might be for the initial password reset request.
 */
public class EmailRequest {
	
	private String email;
	
	public EmailRequest(){}
	
	public EmailRequest(String email){
		this.email = email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
}
