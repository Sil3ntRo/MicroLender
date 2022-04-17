package com.rohansideproject.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rohansideproject.dto.BaseDto;
import com.rohansideproject.response.MarsRoverApiResponse;
import com.rohansideproject.respository.MapPreferencesRepository;
import com.rohansideproject.respository.MarsRoverApiService;

@Controller
public class BaseController {
	
	private String username;
	
	@Autowired
	private MarsRoverApiService roverService;
	
	@GetMapping("/")
	public String getBaseView(ModelMap model, Long userId, Boolean createUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		BaseDto baseDto = createDefaultBaseDto(userId);
		
		if(Boolean.TRUE.equals(createUser) && userId == null) {
			baseDto = roverService.save(baseDto);
		} else {
			baseDto = roverService.findByUserId(userId);
			
			if(baseDto == null) {
				baseDto = createDefaultBaseDto(userId);
			}
		}
		
		baseDto = roverService.findByUserId(userId);
		
		MarsRoverApiResponse roverData = roverService.getRoverData(baseDto);
		model.put("roverData", roverData);
		model.put("baseDto", baseDto);
		model.put("validCameras", roverService.getValidCameras().get(baseDto.getMarsApiRoverData()));
		if(!Boolean.TRUE.equals(baseDto.getRememberPreferences()) && userId != null) {
			BaseDto defaultBaseDto = createDefaultBaseDto(userId);
			roverService.save(defaultBaseDto);
		}
		
		return "index";
	}

	private BaseDto createDefaultBaseDto(Long userId) {
		BaseDto baseDto = new BaseDto();
		baseDto.setMarsApiRoverData("opportuntity");
		baseDto.setMarsSol(1);
		return baseDto;
	}
	
	@PostMapping("/")
	public String postHomeView(BaseDto baseDto) {

		return "redirect:/?userId="+baseDto.getUserId();
	}
}
