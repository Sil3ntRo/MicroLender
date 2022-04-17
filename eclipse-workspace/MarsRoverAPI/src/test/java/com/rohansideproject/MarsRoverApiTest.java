package com.rohansideproject;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MarsRoverApiTest {
	
	@Test
	public void minorTest() {
		System.out.println("Hello there!");
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<String> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=gm0HTUNruqVh6AI88mZHkgy0z0SwuUcmuezloaHu", String.class);
		System.out.println(response.getBody());
	}
}
