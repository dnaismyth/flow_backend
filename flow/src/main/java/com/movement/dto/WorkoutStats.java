package com.movement.dto;

/**
 * Hold stats of a workout 
 * Ex: # of likes, views etc.
 * @author DN
 *
 */
public class WorkoutStats {
	
	private boolean liked;
	
	public boolean isLiked(){
		return liked;
	}
	
	public void setLiked(boolean liked){
		this.liked = liked;
	}
}
