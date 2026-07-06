package com.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lms.entity.Address;
import com.lms.util.ApiResponse;

@ControllerAdvice // helps to recognize this class as exception handling class
public class LibraryManagementExceptionHandler {

	@ExceptionHandler(AddressIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleAddressIdNotFoundException(AddressIdNotFoundException exception)
	{
		
		ApiResponse<String> apiResponse = new ApiResponse<>();

		apiResponse.setData(exception.getMessage());
		apiResponse.setMessage("Address For This Id Not Found");
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LibraryIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleLibraryIdNotFoundException(LibraryIdNotFoundException exception)
	{

		ApiResponse<String> apiResponse = new ApiResponse<>();

		apiResponse.setData(exception.getMessage());
		apiResponse.setMessage("Library For This Id Not Found");
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleUserIdNotFoundException(UserIdNotFoundException exception)
	{

		ApiResponse<String> apiResponse = new ApiResponse<>();

		apiResponse.setData(exception.getMessage());
		apiResponse.setMessage("User For This Id Not Found");
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleBookIdNotFoundException(BookIdNotFoundException exception)
	{

		ApiResponse<String> apiResponse = new ApiResponse<>();

		apiResponse.setData(exception.getMessage());
		apiResponse.setMessage("Book For This Id Not Found");
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); 
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);	// 400
	}
	
	@ExceptionHandler(BookUnableToAddToLibrary.class)
	public ResponseEntity<ApiResponse<String>> BookUnableToAdToLibraryHandler(BookUnableToAddToLibrary exception)
	{

		ApiResponse<String> apiResponse = new ApiResponse<>();

		apiResponse.setData(exception.getMessage());
		apiResponse.setMessage("Book/Library For This Id Not Found");
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); 
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);	// 400
	}
	
	
	@ExceptionHandler(BookUnableToBorrowException.class)
	public ResponseEntity<ApiResponse<String>> BookUnableToBorrowExceptionHandler(BookUnableToBorrowException exception)
	{

		ApiResponse<String> apiResponse = new ApiResponse<>();

		apiResponse.setData(exception.getMessage());
		apiResponse.setMessage("Book/Library For This Id Not Found ");
		apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); 
		return new ResponseEntity<ApiResponse<String>>(apiResponse,HttpStatus.BAD_REQUEST);	// 400
	}
		
}
