package com.example.demo;

public class ImageCount {
	
	private String imageUrl;
	private String count;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
	public ImageCount(String imageUrl, String count) {
		super();
		this.imageUrl = imageUrl;
		this.count = count;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageCount other = (ImageCount) obj;
		
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ImageCount [imageUrl=" + imageUrl + ", count=" + count + "]";
	}
	
	
	

}
