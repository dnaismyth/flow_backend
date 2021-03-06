package com.movement.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movement.dto.Quest;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.BadRequestException;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.QuestRepository;

public class QuestServiceTest extends TestBaseClass {

	@Autowired
	private QuestService questService;
	
	@Autowired
	private QuestRepository questRepo;
	
	private Quest quest;
	private User admin, user;
	private String adminName = "testcreatequest@flow.com";
	private String userName = "testquest@flow.com";
	
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
		
		user = userService.findUserByUsername(userName);
		if(user == null){
			user = new User();
			user.setEmail(userName);
			user.setUsername(userName);
			user.setName(userName);
			user.setPassword("user");
			user.setUserRole(UserRole.USER);
			user = userService.create(user);
		}
			
	}
	
	
	@After
	public void tearDown() throws NoPermissionException{
		if(quest.getId() != null)
			questRepo.delete(quest.getId());
		
		userService.delete(admin, admin.getId());
	}
	
	// Check that a quest can be created
	@Test
	public void testCreateQuest() throws NoPermissionException{
		quest = new Quest();
		quest.setDescription("Test create quest");
		quest.setExperience(100);
		Quest created = questService.createQuest(quest, admin);
		Assert.assertNotNull(created);
	}
	
	// Check that a quest can be updated
	@Test
	public void testUpdateQuest() throws NoPermissionException{
		String title = "Updating quest title.";
		quest = new Quest();
		quest.setDescription("Testing update quest");
		quest.setExperience(20);
		Quest created = questService.createQuest(quest, admin);
		created.setTitle(title);
		created.setExperience(50);
		Quest updated = questService.updateQuest(created, admin);
		Assert.assertEquals(title, updated.getTitle());
		Assert.assertEquals(50, updated.getExperience());	
	}
	
	// Test that a quest can be found by it's id
	@Test
	public void testGetQuestById() throws NoPermissionException{
		quest = new Quest();
		quest.setTitle("Testing find by id");
		Quest created = questService.createQuest(quest, admin);
		Quest found = questService.getQuest(created.getId());
		Assert.assertNotNull(found);
	}
	
	// Test that a quest can be deleted by an admin
	@Test
	public void testDeleteQuest() throws NoPermissionException{
		quest = new Quest();
		quest.setTitle("Testing delete quest");
		Quest toDelete = questService.createQuest(quest, admin);
		boolean deleted = questService.deleteQuest(toDelete.getId(), admin);
		Assert.assertTrue(deleted);
	}
	
	/**
	 * Check that a user can start a new quest
	 * @throws BadRequestException 
	 * @throws NoPermissionException 
	 */
	@Test
	public void testStartNewQuest() throws BadRequestException, NoPermissionException{
		quest = new Quest();
		quest.setTitle("Testing start new quest");
		Quest created = questService.createQuest(quest, admin);
		questService.startNewQuest(created.getId(), user);
		List<Quest> currentQuests = questService.getUsersCurrentQuests(user.getId());
		Assert.assertEquals(1, currentQuests.size());
	}
}
