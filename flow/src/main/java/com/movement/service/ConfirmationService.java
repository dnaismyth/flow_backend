package com.movement.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movement.domain.RConfirmation;
import com.movement.repository.ConfirmationRepository;
import com.movement.util.RestPreconditions;

@Service
public class ConfirmationService {

	@Autowired
	private ConfirmationRepository confirmationRepo;
	
	/**
	 * Create a confirmation instance for a specific user.
	 * This should be called upon initial account creation.
	 * Generate a UUID for the corresponding user.
	 * @param userId
	 */
	public void createUserConfirmationInstance(Long userId, boolean status){
		RestPreconditions.checkNotNull(userId);
		String key = UUID.randomUUID().toString();
		RConfirmation confirm = new RConfirmation(userId, key, status);
		confirmationRepo.save(confirm);
	}
	
	/**
	 * Set the status of a user's confirmation.
	 * @param confirmationKey
	 * @return
	 */
	public boolean setUserConfirmationStatus(String confirmationKey, boolean status){
		RestPreconditions.checkNotNull(confirmationKey);
		RConfirmation rc = confirmationRepo.findByConfirmationKey(confirmationKey);
		if(rc != null){
			rc.setConfirmed(status);
			confirmationRepo.save(rc);
			//TODO: remove key if status is true?
			return true;
		}
		return false;	
	}
	
	/**
	 * Remove the confirmation key
	 * @param rc
	 */
	private void removeConfirmationKey(RConfirmation rc){
		RestPreconditions.checkNotNull(rc);
		rc.setKey(null);
		confirmationRepo.save(rc);
	}

}
