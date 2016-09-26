package com.movement.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.Quest;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.QuestRepository;

public class QuestServiceTest extends TestBaseClass {

	@Autowired
	private QuestService questService;
	
	@Autowired
	private QuestRepository questRepo;
	
	private Quest quest;
	private User admin;
	private String adminName = "testcreatequest@flow.com";
	
	@Before
	public void setUp() throws ResourceNotFoundException{
		admin = userService.findUserByUsername(adminName);
		if(admin == null){
			admin = new User();
			admin.setEmail(adminName);
			admin.setPassword("admin");
			admin.setName(adminName);
			admin.setUsername(adminName);
			admin.setUserRole(UserRole.ADMIN);
			admin = userService.create(admin);
		}
	}
	
	
	@After
	public void tearDown(){
		if(quest.getId() != null)
			questRepo.delete(quest.getId());
		
		userService.delete(admin.getId());
	}
	
	@Test
	public void testCreateQuest() throws NoPermissionException{
		quest = new Quest();
		quest.setDescription("Test create quest");
		quest.setExperience(100);
		Quest created = questService.createQuest(quest, admin);
		Assert.assertNotNull(created);
	}
}
