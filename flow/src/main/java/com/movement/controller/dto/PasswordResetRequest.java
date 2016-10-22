package com.movement.controller.dto;

/**
 * Values necessary for a password reset to be performed.
 * @author DN
 *
 */
public class PasswordResetRequest {
	
	private String password; // new password chosen by the user
	
	private String key;	// UUID from url the user will receive via e-mail
	
	public PasswordResetRequest(){}
	
	public PasswordResetRequest(String password, String key) {
		this.password = password;
		this.key = key;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getKey(){
		return key;
	}
	
	public void setKey(String key){
		this.key = key;
	}

}
