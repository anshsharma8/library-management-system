package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.service.IAddressService;
import com.lms.util.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RequestMapping("/address")
@RestController
public class AddressController {

	@Autowired
	IAddressService addressService;
	
	
	@Operation(operationId="CreateAddress",summary="Adding Address",
			description = "This Rest End Point is Used To Create and Add One Address")
	
	@ApiResponses(value= {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",description="create and returns address entity")})
	@PostMapping
	public ResponseEntity< ApiResponse<Address>> saveAddress(@RequestBody AddressDto addressDto)
	{
		return addressService.saveAddress(addressDto);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<ApiResponse<Address>> findAddressById(@PathVariable int addressId)
	{
		return addressService.findAddressById(addressId);
	}
	
	@PutMapping
	public  ResponseEntity<ApiResponse<Address>> updateAddressComplete(@RequestBody AddressDto addressDto)
	{
		return addressService.updateAddressComplete(addressDto);
	}
	@PatchMapping("/{addressId}")
	public  ResponseEntity<ApiResponse<Address>> updateAddressPartially(@RequestBody AddressDto addressDto,@PathVariable int addressId)
	{
		return addressService.updateAddressPartially(addressDto,addressId);
	}
	
	@DeleteMapping("/{addressId}")
	public  ResponseEntity<ApiResponse<Address>> deleteAddressById(@PathVariable int addressId)
	{
		return addressService.deleteAddressById(addressId);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ApiResponse<List<Address>>> findAllAddress()
	{
		return addressService.findAllAddress();
	}
	
}
