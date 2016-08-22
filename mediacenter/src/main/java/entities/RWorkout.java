package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "workout")
public class RWorkout extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1195501156843452445L;

	@Column(nullable = false)
	private RUser owner;
	
	/**
	 * Stores the activites that were completed during the workout
	 */
	@Embedded
	@ElementCollection
	private List<RActivity> activities = new ArrayList<RActivity>();
	
	@Embedded
	private RLocation location;
	
	public List<RActivity> getActivities(){
		return activities;
	}
	
	public void setActivities(List<RActivity> activities){
		this.activities = activities;
	}
	
	public RUser getOwner(){
		return owner;
	}
	
	public void setOwner(RUser owner){
		this.owner = owner;
	}
	
	public RLocation getLocation(){
		return location;
	}
	
	public void setLocation(RLocation location){
		this.location = location;
	}
}
