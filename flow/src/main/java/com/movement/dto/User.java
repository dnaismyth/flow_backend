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
	private Location location;
	
	private Date createdDate;
	
	/**
	 * Admin, Guest, User
	 */
	private UserRole userRole;
	
	private int level;
		
	
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
	
	public Location getLocation(){
		return location;
	}
	
	public void setLocation(Location location){
		this.location = location;
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
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	

}
