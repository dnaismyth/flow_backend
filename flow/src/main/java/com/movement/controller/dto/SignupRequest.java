package com.movement.controller.dto;

/**
 * Request to store a new user's required fields
 * on signing up for an account.
 * @author DN
 *
 */
public class SignupRequest {

	private String username;
	private String password;
	private String email;
	private String name;
	
	public SignupRequest(){}
	
	public SignupRequest(String username, String name, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}

