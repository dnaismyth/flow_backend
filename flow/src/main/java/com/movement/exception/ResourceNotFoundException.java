package com.movement.exception;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5261600905049703426L;
	public ResourceNotFoundException(){}
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
