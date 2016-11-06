package com.movement.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

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
	
	@Column
	private Point point;
	
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
	
	public Point getPoint(){
		return point;
	}
	
	public void setPoint(Point point){
		this.point = point;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
}
