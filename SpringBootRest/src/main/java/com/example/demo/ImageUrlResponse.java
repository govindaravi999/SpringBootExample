package com.example.demo;

import java.util.List;

public class ImageUrlResponse {
	
	private long jobId;
	private List<ImageCount> imageCount;
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public List<ImageCount> getImageCount() {
		return imageCount;
	}
	public void setImageCount(List<ImageCount> imageCount) {
		this.imageCount = imageCount;
	}
	

	
	

}

