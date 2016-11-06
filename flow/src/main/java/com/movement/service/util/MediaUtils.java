package com.movement.service.util;

import com.movement.util.S3Constants;

public class MediaUtils {

	public static String generateMediaUrl(String fileName){
		if(fileName.startsWith("http")){
			return fileName;
		}
		
		String url = S3Constants.S3_BASE_URL + S3Constants.S3_BUCKET_NAME + "/".concat(fileName);
		return url;
	}
	
}
