package com.movement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;
import com.movement.controller.dto.ResponseList;
import com.movement.controller.dto.TokenResponse;
import com.movement.dto.BaseUser;
import com.movement.dto.User;
import com.movement.exception.NoPermissionException;
import com.movement.service.AwsS3Service;

@RestController
@RequestMapping("/resources")
public class ResourceController extends BaseController {

	@Autowired
	private AwsS3Service awsService;
	/**
	 * Allow user access to S3 Bucket
	 * @return
	 * @throws NoPermissionException 
	 */
	@RequestMapping(value = "/s3token")
	public TokenResponse generateS3Access() throws NoPermissionException{
		User user = getLoggedInUser();
		checkUserPermission(user);
		BasicSessionCredentials credentials = awsService.getS3UserCredentials();
		return new TokenResponse(credentials);
	}
}
