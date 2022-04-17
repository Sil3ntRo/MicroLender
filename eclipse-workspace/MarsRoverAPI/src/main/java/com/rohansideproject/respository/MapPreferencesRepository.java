package com.rohansideproject.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rohansideproject.dto.BaseDto;

public interface MapPreferencesRepository extends JpaRepository<BaseDto, Long> {
	
	// Custom query
	//@Query("select dto from BaseDto dto where userId = :userId")
	BaseDto findByUserId(Long userId);

}
