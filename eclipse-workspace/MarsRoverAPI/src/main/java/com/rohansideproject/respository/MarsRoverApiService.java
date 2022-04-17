package com.rohansideproject.respository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rohansideproject.dto.BaseDto;
import com.rohansideproject.response.MarsPhoto;
import com.rohansideproject.response.MarsRoverApiResponse;


@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "gm0HTUNruqVh6AI88mZHkgy0z0SwuUcmuezloaHu";
	private Map<String, List<String>> validCameras = new HashMap<>();
	
	@Autowired
	private MapPreferencesRepository preferencesRepo;
	
	public MarsRoverApiService() {
		validCameras.put("opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
		validCameras.put("curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
		validCameras.put("spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
	}
	
	public MarsRoverApiResponse getRoverData(BaseDto baseDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RestTemplate rt = new RestTemplate();
		
		List<String> apiUrlEndpoints = getApiUrlEndpoints(baseDto);
		List<MarsPhoto> photos = new ArrayList<>();
		MarsRoverApiResponse  response = new MarsRoverApiResponse();
		
		apiUrlEndpoints.stream()
					   .forEach(url -> {
						   MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
						   photos.addAll(apiResponse.getPhotos());
					   });
		
		response.setPhotos(photos);
		return response;
	}
	
	public List<String> getApiUrlEndpoints (BaseDto baseDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<String> urls = new ArrayList<>();
		
		Method[] methods = baseDto.getClass().getMethods();
		
		
		// Code will fetch all getCameras methods and
		// If it returns true, then it will build an API URL to call in order
		// to fetch pictures for a given rover / camera / sol
		for(Method method : methods) {
			if(method.getName().indexOf("getCamera") > -1 && Boolean.TRUE.equals(method.invoke(baseDto))) {
				String cameraName = method.getName().split("getCamera")[1].toUpperCase();
				if(validCameras.get(baseDto.getMarsApiRoverData()).contains(cameraName)) {
					urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+ baseDto.getMarsApiRoverData() + "photos?sol=" + baseDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=" + cameraName);
				}
			}
		}
		
		return urls;
	}

	public Map<String, List<String>> getValidCameras() {
		return validCameras;
	}

	public BaseDto save(BaseDto baseDto) {
		return preferencesRepo.save(baseDto);
	}

	public BaseDto findByUserId(Long userId) {
		return preferencesRepo.findByUserId(userId);
	}
	
}
