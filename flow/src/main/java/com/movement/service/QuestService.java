package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.RObjective;
import com.movement.domain.RQuest;
import com.movement.domain.RQuestProgress;
import com.movement.domain.RUserQuestPK;
import com.movement.dto.Quest;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.repository.QuestProgressRepository;
import com.movement.repository.QuestRepository;
import com.movement.service.mapper.ObjectiveMapper;
import com.movement.service.mapper.QuestMapper;
import com.movement.service.util.CompareUtil;
import com.movement.util.RestPreconditions;

@Service
public class QuestService {
	
	@Autowired
	private QuestRepository questRepo;
	
	@Autowired
	private QuestProgressRepository questProgressRepo;
	
	private QuestMapper questMapper = new QuestMapper();
	private ObjectiveMapper objMapper = new ObjectiveMapper();

	/**
	 * Allow for an admin to create a quest
	 * @param quest
	 * @param user
	 * @return
	 * @throws NoPermissionException
	 */
	public Quest createQuest(Quest quest, User user) throws NoPermissionException{
		RestPreconditions.checkNotNull(quest);
		RestPreconditions.checkNotNull(user);
		
		if(!user.getUserRole().equals(UserRole.ADMIN)){
			throw new NoPermissionException("Only an admin can create a quest.");
		}
		
		RQuest rq = questMapper.toRQuest(quest);
		RQuest saved = questRepo.save(rq);
		return questMapper.toQuest(saved);
		
	}
	
	public Quest getQuest(Long questId){
		RestPreconditions.checkNotNull(questId);
		RQuest rq = questRepo.findOne(questId);
		return questMapper.toQuest(rq);
	}
	
	
	/**
	 * Allow for an admin to update an existing quest
	 * @param quest
	 * @param user
	 * @return
	 * @throws NoPermissionException
	 */
	public Quest updateQuest(Quest quest, User user) throws NoPermissionException{
		RestPreconditions.checkNotNull(quest);
		RestPreconditions.checkNotNull(user);
		
		if(!user.getUserRole().equals(UserRole.ADMIN)){
			throw new NoPermissionException("Only an admin can update this quest.");
		}
		
		RQuest r = questRepo.findOne(quest.getId());
		if(!CompareUtil.compare(r.getDescription(), quest.getDescription())){
			r.setDescription(quest.getDescription());
		}
		
		if(!CompareUtil.compare(r.getTitle(), quest.getTitle())){
			r.setTitle(quest.getTitle());
		}
		
		if(!CompareUtil.compare(r.getCreatedDate(), quest.getCreatedDate())){
			r.setCreatedDate(quest.getCreatedDate());
		}
		
		if(!CompareUtil.compare(r.getEndTime(), quest.getEndTime())){
			r.setEndTime(quest.getEndTime());
		}
		
		if(!CompareUtil.compare(r.getExperience(), quest.getExperience())){
			r.setExperience(quest.getExperience());
		}
		
		RObjective ro = objMapper.toRObjective(quest.getObjective()); 
		if(!CompareUtil.compare(r.getObjective(), ro)){
			r.setObjective(ro);
		}
		
		if(!CompareUtil.compare(r.getStartTime(), quest.getStartTime())){
			r.setStartTime(quest.getStartTime());
		}
		
		RQuest saved = questRepo.save(r);
		return questMapper.toQuest(saved);
	}
	
	/**
	 * Allow for an admin to delete an existing quest
	 * @param questId
	 * @param user
	 * @return
	 * @throws NoPermissionException 
	 */
	public boolean deleteQuest(Long questId, User user) throws NoPermissionException{
		RestPreconditions.checkNotNull(questId);
		RestPreconditions.checkNotNull(user);
		
		if(!user.getUserRole().equals(UserRole.ADMIN)){
			throw new NoPermissionException("Only an admin can update this quest.");
		}
		
		questRepo.delete(questId);
		return true;
	}
	
	/**
	 * Allow for a user to start a quest
	 * @param questId
	 * @param user
	 * @return
	 * @throws BadRequestException 
	 */
	public boolean startNewQuest(Long questId, User user) throws BadRequestException{
		RestPreconditions.checkNotNull(questId);
		RestPreconditions.checkNotNull(user);
		
		// Check that the user has not already started this quest
		RQuestProgress qp = questProgressRepo.findByUserIdAndQuestId(user.getId(), questId);
		if(qp != null){
			throw new BadRequestException("You have already started this quest.");
		}
		
		RUserQuestPK pk = new RUserQuestPK();
		pk.setQuestId(questId);
		pk.setUserId(user.getId());
		qp = new RQuestProgress();
		qp.setUserQuestPK(pk);
		questProgressRepo.save(qp);
		return true;
	}
	
	
	
}
