package com.movement.dto;

import java.util.Date;
import java.util.Set;

import com.movement.security.Authorities;

public class User extends BaseUser {
	
	private String password;
	
	/**
	 * Unique email
	 */
	private String email;
	
	/**
	 * User bio
	 */
	private String bio;
	
	/**
	 * Phone number
	 */
	private String phone;
	
	/**
	 * User Location
	 */
	private String address;
	
	private Date createdDate;
	
	/**
	 * Admin, Guest, User
	 */
	private UserRole userRole;
		
	
	public User(){}
	
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	
	public String getBio(){
		return bio;
	}
	
	public void setBio(String bio){
		this.bio = bio;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setLoation(String address){
		this.address = address;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setCreatedDate(Date date){
		this.createdDate = date;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public UserRole getUserRole(){
		return userRole;
	}
	
	public void setUserRole(UserRole userRole){
		this.userRole = userRole;
	}
	

}
