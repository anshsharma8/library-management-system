package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.util.ApiResponse;

public interface IAddressService  {

	public ResponseEntity<ApiResponse<Address>> saveAddress(AddressDto addressDto);
	public ResponseEntity<ApiResponse<Address>> findAddressById(int addressId);
	public ResponseEntity<ApiResponse<Address>> updateAddressComplete(AddressDto addressDto);
	public ResponseEntity<ApiResponse<Address>> updateAddressPartially(AddressDto addressDto,int addressId);
	public ResponseEntity<ApiResponse<Address>> deleteAddressById(int addressId);
	public ResponseEntity<ApiResponse<List<Address>>> findAllAddress();
}
