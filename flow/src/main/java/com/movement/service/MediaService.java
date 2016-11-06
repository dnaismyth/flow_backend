package com.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.domain.RMedia;
import com.movement.dto.Media;
import com.movement.dto.User;
import com.movement.dto.UserRole;
import com.movement.exception.NoPermissionException;
import com.movement.exception.ResourceNotFoundException;
import com.movement.repository.MediaRepository;
import com.movement.service.mapper.MediaMapper;
import com.movement.service.util.CompareUtil;
import com.movement.util.RestPreconditions;


@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepo;
	
	@Autowired
	private AwsS3Service awsService;
	
	private MediaMapper mediaMapper = new MediaMapper();
	
	
	/**
	 * Find a media by id
	 * @param id
	 * @return
	 */
	@Transactional
	public Media getMedia(Long id){
		RestPreconditions.checkNotNull(id);
		return mediaMapper.toMedia(mediaRepo.findOne(id));
	}
	
	/**
	 * Create a media and set the owner
	 * @param m
	 * @param owner
	 * @return
	 */
	@Transactional
	public Media createMedia(Media m, User owner){
		RestPreconditions.checkNotNull(m);
		RestPreconditions.checkNotNull(owner);
		m.setOwnerId(owner.getId());
		RMedia rm = mediaMapper.toRMedia(m);
		RMedia saved = mediaRepo.save(rm);
		return mediaMapper.toMedia(saved);
		
	}
	
	/**
	 * Update media properties
	 * @param m
	 * @param owner
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	@Transactional
	public Media updateMedia(Media m, User owner) throws NoPermissionException, ResourceNotFoundException{
		RestPreconditions.checkNotNull(m);
		RestPreconditions.checkNotNull(owner);
		
		RMedia rm = mediaRepo.findOne(m.getOwnerId());
		if(rm == null){
			String message = String.format("Media with id: %s cannot be found.", m.getId());
			throw new ResourceNotFoundException(message);
		}
		
		if(!m.getOwnerId().equals(owner.getId()) && owner.getUserRole() != UserRole.ADMIN){
			throw new NoPermissionException("User does not have access to alter this media");
		}
		
		if(!CompareUtil.compare(m.getCaption(), rm.getCaption())){
			rm.setCaption(m.getCaption());
		}
		
		if(!CompareUtil.compare(m.getFileName(), rm.getFileName())){
			rm.setFileName(m.getFileName());
		}
		
		if(!CompareUtil.compare(m.getOwnerId(), rm.getOwnerId())){
			rm.setOwnerId(m.getOwnerId());
		}
		
		return mediaMapper.toMedia(mediaRepo.save(rm));
		
	}
	
	/**
	 * Delete media item
	 * @param id
	 * @return
	 * @throws NoPermissionException 
	 */
	@Transactional
	public boolean deleteMedia(User user, Long id) throws NoPermissionException{
		RestPreconditions.checkNotNull(id);
		RMedia toDelete = mediaRepo.findOne(id);
		if(!toDelete.getOwnerId().equals(user.getId()) && user.getUserRole() != UserRole.ADMIN){
			throw new NoPermissionException("You must be the owner or admin to modify this media.");
		}
		
		mediaRepo.delete(toDelete);
		return true;
	}
	
}
