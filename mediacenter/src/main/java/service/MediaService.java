package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.MediaRepository;
import service.mapper.MediaMapper;
import service.util.CompareUtil;
import util.RestPreconditions;
import dto.Media;
import dto.User;
import entities.RMedia;
import exception.NoPermissionException;
import exception.ResourceNotFoundException;

@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepo;
	
	private MediaMapper mediaMapper = new MediaMapper();
	
	
	/**
	 * Find a media by id
	 * @param id
	 * @return
	 */
	public Media findMedia(Long id){
		RestPreconditions.checkNotNull(id);
		return mediaMapper.toMedia(mediaRepo.findOne(id));
	}
	
	/**
	 * Create a media and set the owner
	 * @param m
	 * @param owner
	 * @return
	 */
	public Media createMedia(Media m, User owner){
		RestPreconditions.checkNotNull(m);
		RestPreconditions.checkNotNull(owner);
		m.setOwnerId(owner.getId());
		RMedia rm = mediaMapper.toRMedia(m);
		
		return mediaMapper.toMedia(mediaRepo.save(rm));
		
	}
	
	/**
	 * Update media properties
	 * @param m
	 * @param owner
	 * @return
	 * @throws NoPermissionException
	 * @throws ResourceNotFoundException
	 */
	public Media updateMedia(Media m, User owner) throws NoPermissionException, ResourceNotFoundException{
		RestPreconditions.checkNotNull(m);
		RestPreconditions.checkNotNull(owner);
		
		RMedia rm = mediaRepo.findOne(m.getOwnerId());
		if(rm == null){
			String message = String.format("Media with id: %s cannot be found.", m.getId());
			throw new ResourceNotFoundException(message);
		}
		
		if(!m.getOwnerId().equals(owner.getId())){
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
	 */
	public boolean deleteMedia(Long id){
		RestPreconditions.checkNotNull(id);
		mediaRepo.delete(id);
		return true;
	}
	
	
}
