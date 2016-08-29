package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import dto.Relationship;

/**
 * Embedded into RFollow to hold the relationship between two users
 * @author DN
 */
@Embeddable
public class RUserRelationPK {

	@Column(nullable = false)
	private Long follower_id;
	
	@Column(nullable = false)
	private Long following_id;
	
	@Column(nullable = false)
	private Relationship relationship;
	
	public void setFollowerId(Long follower_id){
		this.follower_id = follower_id;
	}
	
	public Long getFollowerId(){
		return follower_id;
	}
	
	public void setFollowingId(Long following_id){
		this.following_id = following_id;
	}
	
	public Long getFollowingId(){
		return following_id;
	}
	
	public void setRelationship(Relationship relationship){
		this.relationship = relationship;
	}
	
	public Relationship getRelationship(){
		return relationship;
	}
}
