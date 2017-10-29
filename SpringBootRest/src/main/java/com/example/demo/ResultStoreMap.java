package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ResultStoreMap {

	
	private static ConcurrentHashMap<Long, ImageUrlResponse> resultMap = new ConcurrentHashMap<>();
	
	
	public static void addInitialEntry(Long jobId,ImageUrlRequest request) {
		
		ImageUrlResponse response=new ImageUrlResponse();
		response.setJobId(jobId);
		List<String> urls=request.getUrl();
		List<ImageCount> imageCountList=new ArrayList<ImageCount>();
		for(String url:urls) {
			ImageCount image=new ImageCount();
			image.setImageUrl(url);
			image.setCount("pending");
			imageCountList.add(image);
		}
		response.setImageCount(imageCountList);
		resultMap.put(jobId, response);
	}
	
public static ImageUrlResponse getJobStatus(Long jobId) {
		
	
		return resultMap.get(jobId);
	}
}
