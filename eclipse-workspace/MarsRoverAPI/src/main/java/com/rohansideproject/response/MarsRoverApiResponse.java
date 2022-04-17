package com.rohansideproject.response;

import java.util.List;
import java.util.ArrayList;

public class MarsRoverApiResponse {
	
	List<MarsPhoto> photos = new ArrayList<>();
	
	public List<MarsPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<MarsPhoto> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "MarsRoverApiResponse [photos=" + photos + "]";
	}
}
