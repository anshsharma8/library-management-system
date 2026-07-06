package com.lms.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.entity.User;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.UserIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.service.IAddressService;
import com.lms.util.ApiResponse;

@Service
public class AddressService implements IAddressService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public ResponseEntity<ApiResponse<Address>> saveAddress(AddressDto addressDto) {
		
		Address address = modelMapper.map(addressDto,Address.class);
		addressRepository.save(address);
		
		ApiResponse<Address> apiResponse = new ApiResponse<>();
		apiResponse.setData(address);
		apiResponse.setMessage("Address Saved Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 ok
		return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.OK);
		
		
	}

      @Override
	  public ResponseEntity<ApiResponse<Address>> findAddressById(int addressId) {
		
      Optional<Address> optional = addressRepository.findById(addressId);
      ApiResponse<Address> apiResponse = new ApiResponse<>();
	     if(optional.isPresent())
	     {
	      Address address =optional.get();

			
			apiResponse.setData(address);
			apiResponse.setMessage("Address Found Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 ok
			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.OK);// matching postman and Apiresonse
	      
	     }
	     else
	     {

			throw new AddressIdNotFoundException("Invalid Address Id");
		 }
	}

     
	   @Override
	   public  ResponseEntity<ApiResponse<Address>> updateAddressComplete(AddressDto addressDto) {
		// TODO Auto-generated method stub
		   ApiResponse<Address> apiResponse = new ApiResponse<>();
		   Address address = modelMapper.map(addressDto, Address.class);
		   addressRepository.save(address);
		   
		    apiResponse.setData(address);
			apiResponse.setMessage("Address Updated");
			apiResponse.setStatusCode(HttpStatus.OK.value()); 
			return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.OK);
		
		}
	   

	   @Override
	   public ResponseEntity<ApiResponse<Address>> updateAddressPartially(AddressDto addressDto, int addressId) {
		   Optional<Address> optional = addressRepository.findById(addressId);
			ApiResponse<Address> apiResponse = new ApiResponse<>();
			
			
			if(optional.isPresent())
			{
				Address address = optional.get();
				
				
				if(addressDto.getArea()!=null) {
				
				address.setArea(addressDto.getArea());
				
				}
				
				if(addressDto.getCity()!=null) {
					
					address.setCity(addressDto.getCity());
					
				}

				if(addressDto.getCountry()!=null) {
					
					address.setCountry(addressDto.getCountry());
				}          
				
				if(addressDto.getHouseNumber()!=0) {
					
					address.setHouseNumber(addressDto.getHouseNumber());
				}
				
                if(addressDto.getState()!=null) {
					
					address.setState(addressDto.getState());
				}
               if(addressDto.getPincode()!=0) {
					
					address.setPincode(addressDto.getPincode());
				}
				addressRepository.save(address);
				apiResponse.setData(address);
				apiResponse.setMessage("User Updated Successfully");
				apiResponse.setStatusCode(HttpStatus.OK.value());

				return new ResponseEntity<ApiResponse<Address>>(apiResponse, HttpStatus.OK);
				
			}
			else
			{
				throw new AddressIdNotFoundException("Address Id Not Found");
			}
	   }

	   @Override
	   public ResponseEntity<ApiResponse<Address>> deleteAddressById(int addressId) {
		// TODO Auto-generated method stub
		addressRepository.deleteById(addressId);
		ApiResponse<Address> apiResponse = new ApiResponse<>();
		apiResponse.setData(null);
		apiResponse.setMessage("Address Deleted");
		apiResponse.setStatusCode(HttpStatus.OK.value()); 
		return new ResponseEntity<ApiResponse<Address>>(apiResponse,HttpStatus.OK);
		
	   }

	   @Override
	   public ResponseEntity<ApiResponse<List<Address>>> findAllAddress() {
		List<Address> allAddress = addressRepository.findAll();
		ApiResponse<List<Address>> apiResponse = new ApiResponse<>();
		apiResponse.setData(allAddress);
		apiResponse.setMessage("Address Fetched");
		apiResponse.setStatusCode(HttpStatus.OK.value()); 
		return new ResponseEntity<ApiResponse<List<Address>>>(apiResponse,HttpStatus.OK);
	   }

		  
	   
		  
	   

	
}
