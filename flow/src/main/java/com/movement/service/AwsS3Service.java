package com.movement.service;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.Statement.Effect;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetFederationTokenRequest;
import com.amazonaws.services.securitytoken.model.GetFederationTokenResult;
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

	//TODO: read from properties to input credentials
	public BasicSessionCredentials getS3UserCredentials() {
		AWSSecurityTokenServiceClient stsClient = new AWSSecurityTokenServiceClient(new BasicAWSCredentials("", ""));

		GetFederationTokenRequest getFederationTokenRequest = new GetFederationTokenRequest();
		getFederationTokenRequest.setDurationSeconds(7200);
		getFederationTokenRequest.setName("User");

		// Define the policy and add to the request.
		Policy policy = new Policy();
		policy.withStatements(new Statement(Effect.Allow).withActions(
				S3Actions.ListObjects)
				.withResources(new Resource("arn:aws:s3:::".concat(S3Constants.S3_BUCKET_NAME))));

		getFederationTokenRequest.setPolicy(policy.toJson());

		// Get the temporary security credentials.
		GetFederationTokenResult federationTokenResult = stsClient
				.getFederationToken(getFederationTokenRequest);
		Credentials sessionCredentials = federationTokenResult.getCredentials();

		// Package the session credentials as a BasicSessionCredentials
		// object for an S3 client object to use.
		BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(
				sessionCredentials.getAccessKeyId(),
				sessionCredentials.getSecretAccessKey(),
				sessionCredentials.getSessionToken());
		
		return basicSessionCredentials;
	}
}
