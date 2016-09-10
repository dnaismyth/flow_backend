package com.movement.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AwsServiceTest extends TestBaseClass{
	
	@Autowired
	private AwsS3Service service;
	
	@Test
	public void testS3Upload(){	
		//String uploadFile = "C:/Users/dayna/Pictures/IMG_4424.jpg";
		//String fileName = "IMG_4424.jpg";			
		//service.uploadFile(uploadFile, fileName);
	}

	
}
