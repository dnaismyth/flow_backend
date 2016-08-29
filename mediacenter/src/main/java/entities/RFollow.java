package entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="follow")
public class RFollow extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7564388153453934400L;
	
	/**
	 * Represents the current logged in user
	 */
//	@Column(name="user_id", nullable = false)
//	private Long userId;
	
	@Embedded
	private RUserRelationPK userRelation;
	
	public RUserRelationPK getUserRelation(){
		return userRelation;
	}
	
	public void setUserRelation(RUserRelationPK userRelation){
		this.userRelation = userRelation;
	}

	
	
}
