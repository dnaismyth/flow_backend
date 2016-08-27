package entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import dto.UserRole;


/**
 * Stores user information and login
 */
@Entity
@Table(name="login_user")
public class RUser extends BaseEntity {

	private static final long serialVersionUID = -2773181923604283810L;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@Embedded
	private RLocation location;
	
	@Column
	private String bio;
	
	@Column(nullable = false)
	private String email;
	
	@Column
	private String phone;
	
	@Column(name="userrole")
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public RLocation getLocation(){
		return location;
	}
	
	public void setLocation (RLocation location){
		this.location = location;
	}
	
	public String getBio(){
		return bio;
	}
	
	public void setBio(String bio){
		this.bio = bio;
	}
	
	public String getUsername(){
		return userName;
	}
	
	public void setUsername(String userName){
		this.userName = userName;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public UserRole getRoleType(){
		return role;
	}
	
	public void setUserRole(UserRole role){
		this.role = role;
	}
	
	//TODO: encode password
//	private String encode(String str) {
//        
//    }
	
	

}
