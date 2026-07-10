package com.lms.controller;

import com.lms.dto.LoginRequestDto;
import com.lms.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.serviceimplementation.UserService;
import com.lms.util.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	 

		@Autowired
		UserService userService;


		@PostMapping("/{addressId}")
		public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody UserDto userDto,@PathVariable int addressId) {

			return userService.registerUser(userDto, addressId);
		}

		@GetMapping("/{userId}")
		public ResponseEntity<ApiResponse<User>> findUserById(
				@PathVariable int userId, @AuthenticationPrincipal UserDetails userDetails) {

			return userService.findUserById(userId,userDetails);
		}

		@PutMapping
		public ResponseEntity<ApiResponse<User>> updateUser(
				@RequestBody UserDto userDto) {

			return userService.updateUserComplete(userDto);
		}
		
		@PatchMapping("/{userId}")
		public ResponseEntity<ApiResponse<User>> updateUserPartially(
				@RequestBody UserDto userDto,@PathVariable int userId) {

			return userService.updateUserPartially(userDto,userId);
		}

		@DeleteMapping("/{userId}")
		public ResponseEntity<ApiResponse<User>> deleteUserById(
				@PathVariable int userId) {

			return userService.deleteUserById(userId);
		}

		@GetMapping("/fetchAll")
		public ResponseEntity<ApiResponse<List<User>>> findAllUser() {

			return userService.findAllUser();
		}
		

		@PutMapping("/borrow/{userId}/{bookId}")
		public ResponseEntity<ApiResponse<User>> borrowBook(@PathVariable int userId,@PathVariable int bookId) {

			return userService.borrowBookByUser(userId, bookId);
		}
		


		@PutMapping("/return/{bookId}")
		public ResponseEntity<ApiResponse<User>> returnBook(@PathVariable int bookId) {

			return userService.returnBook(bookId);
		}

		@PostMapping("/login")
	    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto)
		{
           return userService.login(loginRequestDto);
		}
	}

