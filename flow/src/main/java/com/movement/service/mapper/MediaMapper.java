package com.movement.service.mapper;

import com.movement.domain.RMedia;
import com.movement.dto.Media;

/**
 * Used to map RMedia and Media 
 * @author DN
 *
 */
public class MediaMapper {

	/**
	 * To Media Object
	 * @param rm
	 * @return
	 */
	public Media toMedia(RMedia rm){
		Media m = null;
		if(rm != null){
			m = new Media();
			m.setCaption(rm.getCaption());
			m.setOwnerId(rm.getOwnerId());
			m.setFileName(rm.getFileName());
		}
		
		return m;
	}
	
	/**
	 * To Media Entity
	 * @param m
	 * @return
	 */
	public RMedia toRMedia(Media m){
		RMedia rm = null;
		if(m != null){
			rm = new RMedia();
			rm.setCaption(m.getCaption());
			rm.setOwnerId(m.getOwnerId());
			rm.setFileName(m.getFileName());
		}
		return rm;
	}
}
