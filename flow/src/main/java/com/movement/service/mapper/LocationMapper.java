package com.movement.service.mapper;

import org.apache.log4j.Logger;

import com.movement.domain.RLocation;
import com.movement.dto.Location;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;


public class LocationMapper {
	protected static final Logger logger = Logger.getLogger(LocationMapper.class); 

	/**
	 * To location object
	 * @param rl
	 * @return
	 */
	public Location toLocation(RLocation rl){
		Location l = null;
		if(rl != null){
			l = new Location();
			l.setLatitude(rl.getLatitude());
			l.setLongitude(rl.getLongitude());
			l.setAddress(rl.getAddress());
		}
		
		return l;
	}
	
	/**
	 * To Entity Location
	 * @param l
	 * @return
	 */
	public RLocation toRLocation(Location l){
		RLocation rl = null;
		if(l != null){
			rl = new RLocation();
			rl.setLatitude(l.getLatitude());
			rl.setLongitude(l.getLongitude());
			rl.setAddress(l.getAddress());
			Geometry geom = wktToGeometry(l.getLongitude(), l.getLatitude());
			//rl.setPoint((Point) geom);
		}
		
		return rl;
	}
	
	/**
	 * Convert long/lat into geometry
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	private Geometry wktToGeometry(float longitude, float latitude) {
		
		GeometryFactory gf = new GeometryFactory();
		Point point = gf.createPoint(new Coordinate(longitude, latitude));
		String wktPoint = point.toText();
		WKTReader fromText = new WKTReader();
		Geometry geom = null;
		try {
			geom = fromText.read(wktPoint);
		} catch (ParseException e) {
			throw new RuntimeException("Not a WKT string:" + wktPoint);
		}
		return geom;
	}
	
}
