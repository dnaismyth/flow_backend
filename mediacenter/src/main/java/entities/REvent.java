package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Stores information on a workout event for others to see
 * and join.
 * @author DN
 *
 */
@Entity
@Table(name="event")
public class REvent extends BaseEntity {
	
	@Column(nullable=false)
	private RUser owner;
	
	public RUser getOwner(){
		return owner;
	}
	
	public void setOwner(RUser owner){
		this.owner = owner;
	}
	
}
