package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dto.TextLength;
import dto.UserRole;


/**
 * Stores user information and login
 */
@Entity
@Table(name="login_user")
public class RUser extends BaseEntity {

	private static final long serialVersionUID = -2773181923604283810L;
	
	@Column(name="username", nullable = false, unique=true)
	private String username;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private boolean activated = false;
	
	@Column(length=TextLength.ACTIVATION_KEY)
	private String activationKey;
	
	@Column(name = "resetpasswordkey", length=TextLength.ACTIVATION_KEY)
	private String resetPasswordKey;
	
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
	
	@Column(name="avatar")
	private String avatar;
	
	public String getName(){
		return name;
	}
	
	@ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
	private Set<Authority> authorities;
	
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
	
	public String getAvatar(){
		return avatar;
	}
	
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}
	

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public void setResetPasswordKey(String resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
}
	
	

}
