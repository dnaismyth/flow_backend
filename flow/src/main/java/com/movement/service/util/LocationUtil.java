package com.movement.service.util;

import com.movement.domain.RLocation;
import com.movement.dto.Location;

/**
 * Utilities for location
 * @author DN
 *
 */
public class LocationUtil {

	public static boolean compareLocation(RLocation rl, Location l){
		
		if(rl == null){
			return l == null;
		}
		
		if(l == null){
			return rl == null;
		}
		
		if(!CompareUtil.compare(rl.getAddress(), l.getAddress())){
			return false;
		}
		
		if(!CompareUtil.compare(rl.getLatitude(), l.getLatitude())){
			return false;
		}
		
		if(!CompareUtil.compare(rl.getLongitude(), l.getLongitude())){
			return false;
		}
		
		return true;
	}
}
