package dto;

public class User {

	/**
	 * Unique username
	 */
	private String username;
	
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
	
	
	public User(){}
	
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
	

}
