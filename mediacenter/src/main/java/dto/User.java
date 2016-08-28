package dto;

import java.util.Date;

public class User {

	private Long id;
	
	/**
	 * Unique username
	 */
	private String username;
	
	private String password;
	
	/**
	 * Unique email
	 */
	private String email;
	
	/**
	 * User avatar image
	 */
	private String avatar;
	
	/**
	 * Alternate name of a user (does not have to be unique)
	 */
	private String name;
	
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
	private UserRole roleType;
		
	
	public User(){}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getAvatar(){
		return avatar;
	}
	
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
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
		return roleType;
	}
	
	public void setUserRole(UserRole roleType){
		this.roleType = roleType;
	}
	
	

}
