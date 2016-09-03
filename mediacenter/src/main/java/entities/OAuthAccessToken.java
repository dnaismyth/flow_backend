package entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dto.TextLength;

@Entity
@Table(name="oauth_access_token")
public class OAuthAccessToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1456423047899405199L;
	
	@Id
	private Long id;

	@Column(name="token_id", length=TextLength.TOKEN)
	private String tokenId = null;
	
	@Column(name="token")
	private Blob token;
	
	@Column(name="authentication_id", length=TextLength.TOKEN)
	private String authId = null;
	
	@Column(name="username")
	private String username = null;
	
	@Column(name="client_id")
	private String cliendId = null;
	
	@Column
	private Blob authentication;
	
	@Column(name="refresh_token", length=TextLength.TOKEN)
	private String refreshToken = null;
}
