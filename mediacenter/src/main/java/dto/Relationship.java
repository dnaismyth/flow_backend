package dto;

/**
 * Relationship between two users
 * @author DN
 *
 */
public enum Relationship {
	/**
	 * User ignore, will not be able to see their posts
	 */
	IGNORE,
	/**
	 * Follow a user, their workouts will show in the main feed
	 */
	FOLLOW
}
