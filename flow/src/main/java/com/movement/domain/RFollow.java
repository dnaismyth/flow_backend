package com.movement.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.movement.dto.Relationship;

@Entity
@Table(name="follow", indexes = {
		@Index(columnList = "target_id", name = "idx_follow_target_id")
})
public class RFollow extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7564388153453934400L;
	
	@Embedded
	private RUserRelationPK userRelation;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Relationship relationship;
	
	public RUserRelationPK getUserRelation(){
		return userRelation;
	}
	
	public void setUserRelation(RUserRelationPK userRelation){
		this.userRelation = userRelation;
	}
	
	public void setRelationship(Relationship relationship){
		this.relationship = relationship;
	}
	
	public Relationship getRelationship(){
		return relationship;
	}

	
	
}
