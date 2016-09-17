package com.movement.dto;

/**
 * The privacy level of a user's posts
 * Further implementation later, by default workouts
 * will be set to public.
 * @author DN
 *
 */
public enum ShowType {
	/**
	 * Only seen by the logged in user
	 */
	PRIVATE,
	
	/**
	 * Posted to everyone
	 */
	PUBLIC,
		
	/**
	 * Posted to selected users
	 */
	CUSTOM
}
