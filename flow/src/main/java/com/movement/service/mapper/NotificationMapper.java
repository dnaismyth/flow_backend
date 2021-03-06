package com.movement.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.movement.domain.RNotification;
import com.movement.dto.Notification;

/**
 * Class to map Notification and RNotification
 * @author DN
 *
 */
public class NotificationMapper {
	
	/**
	 * To Notification DTO
	 * @param rn
	 * @return
	 */
	public Notification toNotification(RNotification rn){
		Notification n = null;
		if(rn != null){
			n = new Notification(rn.getUserId(), rn.getNotifyType());
			n.setCreatedDate(rn.getCreatedDate());
		}
		
		return n;
	}
	
	/**
	 * To Entity Notification
	 * @param n
	 * @return
	 */
	public RNotification toRNotification(Notification n){
		RNotification rn = null;
		if(n != null){
			rn = new RNotification();
			rn.setCreatedDate(n.getCreatedDate());
			rn.setUserId(n.getUserId());
			rn.setNotifyType(n.getNotifyType());
		}
		
		return rn;
	}
	
	public List<Notification> toNotificationList(List<RNotification> rNotify){
		List<Notification> notifyList = new ArrayList<Notification>();
		for(RNotification rn : rNotify ){
			notifyList.add(toNotification(rn));
		}
		return notifyList;
	}
}
