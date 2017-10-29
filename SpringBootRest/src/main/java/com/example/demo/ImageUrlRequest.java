package com.example.demo;

import java.util.List;

public class ImageUrlRequest {

    private List<String> url;
    
    
    public ImageUrlRequest() {
		super();
		
	}
    

	public ImageUrlRequest(List<String> url) {
		super();
		this.url = url;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}
    
    
    
    
}