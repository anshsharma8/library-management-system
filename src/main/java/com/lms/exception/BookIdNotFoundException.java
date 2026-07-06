package com.lms.exception;

public class BookIdNotFoundException extends RuntimeException {

	public BookIdNotFoundException(String message)  {
		super(message);
	}

}
