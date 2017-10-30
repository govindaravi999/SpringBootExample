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
		System.out.println("urlsssssssssssssss"+urls);
		List<ImageCount> imageCountList=new ArrayList<ImageCount>();
		for(String url:urls) {
			ImageCount image=new ImageCount(url,"pending");
			imageCountList.add(image);
		}
		response.setImageCount(imageCountList);
		System.err.println("image count::::::::::::::::::"+imageCountList);
		resultMap.put(jobId, response);
	}
	
public static ImageUrlResponse getJobStatus(Long jobId) {
		
	
		return resultMap.get(jobId);
	}


public static void updateEntry(Long jobId,ImageCount result) {
	
	
	ImageUrlResponse currentRespone= resultMap.get(jobId);
	if(currentRespone==null) return;
	
	for(ImageCount count:currentRespone.getImageCount()) {
		
		if(count.getImageUrl()==result.getImageUrl()) {
			System.out.println("fond match for url:"+(count.getImageUrl()+"and count:"+result.getCount()));
			count.setCount(result.getCount());
			break;
		}
	}
	
	
}

}
