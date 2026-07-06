package com.lms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private int userId;
	private String userName;
	private long phoneNumber;
	private String email;
	private String password;
	private String role;
}
