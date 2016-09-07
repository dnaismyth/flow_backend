package com.movement.domain;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;

import com.movement.config.SecurityConfiguration;
import com.movement.dto.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

@Table(name="flow_user")
@Entity
public class RUser {
	
	@Id
	@GeneratedValue
    private Long id;

    @Column(updatable = false, nullable = false, unique=true)
    @Size(min = 0, max = 50)
    private String username;
    
    @Column(nullable = false)
	private String name;

    @Size(min = 0, max = 500)
    private String password;
    
    @Column(name="role")
    private UserRole userRole;

    /**
	 * Date of creation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable = false)
	private Date createdDate;
	
    @Email
    @Size(min = 0, max = 50)
    private String email;

    private boolean activated;
    
    @Embedded
	private RLocation location;
    
	@Column
	private String bio;
	
	@Column
	private String phone;
	
	@Column(name="avatar")
	private String avatar;

    @Size(min = 0, max = 100)
    @Column(name = "activationkey")
    private String activationKey;

    @Size(min = 0, max = 100)
    @Column(name = "resetpasswordkey")
    private String resetPasswordKey;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private Set<Authority> authorities;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    public RLocation getLocation(){
		return location;
	}
	
	public void setLocation (RLocation location){
		this.location = location;
	}

    public String getPassword() {
        return password;
    }
    
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getAvatar(){
		return avatar;
	}
	
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

    public void setPassword(String password) {
    	this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public UserRole getUserRole(){
    	return userRole;
    }
    
    public void setUserRole(UserRole userRole){
    	this.userRole = userRole;
    }
    
    public Long getId(){
    	return id;
    }
    
    public void setId(Long id){
    	this.id = id;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
    
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate){
		this.createdDate = createdDate;
	}
	@PrePersist
    protected void onCreate() {
		createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	createdDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RUser user = (RUser) o;

        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", activated='" + activated + '\'' +
                ", activationKey='" + activationKey + '\'' +
                ", resetPasswordKey='" + resetPasswordKey + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
