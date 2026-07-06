package com.lms.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

	private String message;
	private int statusCode;
	private T data;
}
// datat type should be generic
// apiresponse<Address>
//Address data