package com.movement.service.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.movement.domain.RQuest;
import com.movement.dto.Quest;

/**
 * Mapper for Quest DTO and RQuest
 * @author DN
 *
 */
public class QuestMapper {

	private ObjectiveMapper objectiveMapper = new ObjectiveMapper();
	
	/**
	 * To Quest object
	 * @param rq
	 * @return
	 */
	public Quest toQuest(RQuest rq){
		Quest q = null;
		if(rq != null){
			q = new Quest();
			q.setCreatedDate(rq.getCreatedDate());
			q.setDescription(rq.getDescription());
			q.setEndTime(rq.getEndTime());
			q.setStartTime(rq.getStartTime());
			q.setExperience(rq.getExperience());
			q.setId(rq.getId());
			q.setTitle(rq.getTitle());
			q.setObjective(objectiveMapper.toObjective(rq.getObjective()));
			q.setStatus(rq.getStatus());
		}
		
		return q;
	}
	
	/**
	 * To Entity Quest
	 * @param q
	 * @return
	 */
	public RQuest toRQuest(Quest q){
		RQuest rq = null;
		if(q != null){
			rq = new RQuest();
			rq.setCreatedDate(q.getCreatedDate());
			rq.setDescription(q.getDescription());
			rq.setEndTime(q.getEndTime());
			rq.setExperience(q.getExperience());
			rq.setId(q.getId());
			rq.setStartTime(q.getStartTime());
			rq.setTitle(q.getTitle());
			rq.setObjective(objectiveMapper.toRObjective(q.getObjective()));
			rq.setStatus(q.getStatus());
		}
	
		return rq;
	}
	
	/**
	 * Return a list of Quests mapped from RQuests
	 * @param rqList
	 * @return
	 */
	public List<Quest> toQuestList(List<RQuest> rqList){
		List<Quest> questCol = null;
		if(rqList != null){
			questCol = new ArrayList<Quest>();
			for(RQuest rq : rqList){
				questCol.add(toQuest(rq));
			}
		}
		return questCol;
	}
}
