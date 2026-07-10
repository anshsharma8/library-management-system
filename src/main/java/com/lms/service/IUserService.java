package com.lms.service;

import java.util.List;

import com.lms.dto.LoginRequestDto;
import com.lms.dto.LoginResponseDto;
import org.springframework.http.ResponseEntity;
import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.util.ApiResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
	public ResponseEntity<ApiResponse<User>> registerUser(UserDto userDto,int addressId);
	public ResponseEntity<ApiResponse<User>> findUserById(int userId, UserDetails userDetails);
	public ResponseEntity<ApiResponse<User>>updateUserComplete(UserDto userDto);
	public ResponseEntity<ApiResponse<User>>updateUserPartially(UserDto userDto,int userId);
	public ResponseEntity<ApiResponse<User>>deleteUserById(int userId);
	public ResponseEntity<ApiResponse<List<User>>> findAllUser();
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId,int bookId);
	public ResponseEntity<ApiResponse<User>> returnBook(int bookId);
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(LoginRequestDto loginRequestDto);

	
	
}

