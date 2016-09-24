package com.movement.dto;

/**
 * Location object for events and users
 * @author DN
 *
 */
public class Location {

	private String address;
	private float latitude;
	private float longitude;
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setLatitude(float latitude){
		this.latitude = latitude;
	}
	
	public float getLatitude(){
		return latitude;
	}
	
	public void setLongitude(float longitude){
		this.longitude = longitude;
	}
	
	public float getLongitude(){
		return longitude;
	}
}
