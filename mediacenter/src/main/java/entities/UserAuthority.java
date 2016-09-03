package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dto.User;

@Entity
@Table(name="user_authority", indexes = {
		@Index(name="user_authority_idx_1", columnList="username, name")})
public class UserAuthority {
	
	@Id
	private Long id;

	@OneToOne
	@JoinColumn(name="username")
	private RUser username;
	
	@OneToOne
	@JoinColumn(name="name")
	private Authority authority;
}
