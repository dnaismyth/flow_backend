package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Location information used for Workout Event location and User location
 */
@Embeddable
public class RLocation {

	@Column
	private float latitude;
	
	@Column
	private float longitude;
	
	@Column
	private String address;
	
	public void setLatitude(float latitude){
		this.latitude = latitude;
	}
	
	public float getLatitude(){
		return latitude;
	}
	
	public void setLongitude(float longitude){
		this.longitude = longitude;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
}
