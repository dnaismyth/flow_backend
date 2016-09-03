package entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.TextLength;

@Entity
@Table(name="oauth_refresh_token")
public class OAuthRefreshToken {
	
	@Id
	private Long id;
	
	@Column(name="token_id", length=TextLength.TOKEN)
	private String tokenId = null;
	
	@Column
	private Blob token;
	
	@Column
	private Blob authentication;
}
