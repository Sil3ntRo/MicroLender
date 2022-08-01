package com.rohansideproject.securityapp.user.dto;


public class UserDTO {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private String occupation;
	
	public UserDTO() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
