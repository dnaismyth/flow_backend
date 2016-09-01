package dto;

/**
 * DTO To store basic user info
 * @author DN
 *
 */
public class BaseUser {
	
	private Long id;
	
	/**
	 * User profile avatar
	 */
	private String avatar;
	
	/**
	 * Unique username
	 */
	private String username;
	
	/**
	 * Alternate user nickname
	 */
	private String name;
	
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
}
