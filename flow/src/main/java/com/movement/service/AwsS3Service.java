package com.movement.service;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
	public void uploadFile(String uploadFile, String filename) {
			
		String fileNameInS3 = filename;
			
		s3client.putObject(
			new PutObjectRequest(S3Constants.S3_BUCKET_NAME, 
				fileNameInS3, new File(uploadFile))
		.withCannedAcl(CannedAccessControlList.PublicRead));
	}
}
