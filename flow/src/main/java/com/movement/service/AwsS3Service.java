package com.movement.service;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.movement.dto.User;
import com.movement.util.RestPreconditions;
import com.movement.util.S3Constants;

@Service
@Transactional
public class AwsS3Service extends ServiceBase {

	@Autowired
	private AmazonS3 s3client;

	/**
	 * Upload a file to default s3 bucket
	 * @param uploadFile
	 * @param filename
	 */
	public void uploadFile(User user, String uploadFile) {
		RestPreconditions.checkNotNull(user);
		RestPreconditions.checkNotNull(user.getId());
		RestPreconditions.checkNotNull(uploadFile);
			
		String fileLocation = S3Constants.S3_BUCKET_NAME + "/" + user.getId(); // store each user's images in their unique file
		String fileNameInS3 = String.format("%s-media", user.getId());	// format filename for unique user		
		s3client.putObject(
			new PutObjectRequest(fileLocation, 
				fileNameInS3, new File(uploadFile))
		.withCannedAcl(CannedAccessControlList.PublicRead));
	}
}
