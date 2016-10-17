package com.movement.controller.dto;

import com.amazonaws.auth.BasicSessionCredentials;

/**
 * Used to pass through s3 tokens for a user session
 * @author DN
 *
 */
public class TokenResponse {
	
	private BasicSessionCredentials credentials;
	
	public TokenResponse(BasicSessionCredentials credentials){
		this.credentials = credentials;
	}
	
	public void setCredentials(BasicSessionCredentials credentials){
		this.credentials = credentials;
	}
	
	public BasicSessionCredentials getCredentials(){
		return credentials;
	}
}
