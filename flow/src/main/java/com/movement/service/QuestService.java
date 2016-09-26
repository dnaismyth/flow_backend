package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.RQuest;
import com.movement.dto.Quest;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.NoPermissionException;
import com.movement.repository.QuestRepository;
import com.movement.service.mapper.QuestMapper;
import com.movement.util.RestPreconditions;

@Service
public class QuestService {
	
	@Autowired
	private QuestRepository questRepo;
	
	private QuestMapper questMapper = new QuestMapper();

	public Quest createQuest(Quest quest, User user) throws NoPermissionException{
		RestPreconditions.checkNotNull(quest);
		RestPreconditions.checkNotNull(user);
//		
//		if(!user.getUserRole().equals(UserRole.ADMIN)){
//			throw new NoPermissionException("Only an admin can create a quest.");
//		}
		
		RQuest rq = questMapper.toRQuest(quest);
		RQuest saved = questRepo.save(rq);
		return questMapper.toQuest(saved);
		
	}
}
