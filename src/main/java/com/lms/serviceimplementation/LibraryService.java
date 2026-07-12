package com.lms.serviceimplementation;

import com.lms.exception.BookIdNotFoundException;
import com.lms.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.dto.BookDto;
import com.lms.dto.LibraryDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.BookUnableToAddToLibrary;
import com.lms.exception.LibraryIdNotFoundException;
import com.lms.entity.Library;
import com.lms.repository.AddressRepository;
import com.lms.repository.LibraryRepository;
import com.lms.service.ILibraryService;
import com.lms.util.ApiResponse;

@Service
public class LibraryService implements ILibraryService{

	private final BookRepository bookRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	LibraryRepository libraryRepository;

	LibraryService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto, int addressId) {
		Library library = modelMapper.map(libraryDto, Library.class);
		Optional<Address> optional = addressRepository.findById(addressId);
		 ApiResponse<Library> apiResponse = new ApiResponse<>();
		if (optional!=null) {
			Address address = optional.get();
			library.setAddress(address);
		    libraryRepository.save(library);

			apiResponse.setData(library);
			apiResponse.setMessage("Library saved Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 ok
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);
		} 
		else {

			apiResponse.setData(null);
			apiResponse.setMessage("Address For This Id Not Found, Library Failed To Save");
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value()); // 400
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> findLibraryById(int libraryId) {
		Optional<Library> optional = libraryRepository.findById(libraryId);
	      ApiResponse<Library> apiResponse = new ApiResponse<>();
		     if(optional.isPresent())
		     {
		      Library library =optional.get();

				
				apiResponse.setData(library);
				apiResponse.setMessage("Library Found Successfully");
				apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 ok
				return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);// matching postman and Apiresonse
		      
		     }
		     else
		     {
                throw new LibraryIdNotFoundException("Invalid Library Id");
	         }
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> updateLibraryComplete(LibraryDto libraryDto) {
		// TODO Auto-generated method stub
		   ApiResponse<Library> apiResponse = new ApiResponse<>();
		   Library library = modelMapper.map(libraryDto, Library.class);
		   libraryRepository.save(library);
		   
		    apiResponse.setData(library);
			apiResponse.setMessage("Library Updated");
			apiResponse.setStatusCode(HttpStatus.OK.value()); 
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);
	}
	

	@Override
	public ResponseEntity<ApiResponse<Library>> updateLibraryPartially(LibraryDto libraryDto, int libraryId) {
		 Optional<Library> optional = libraryRepository.findById(libraryId);
		  ApiResponse<Library> apiResponse = new ApiResponse<>();
		  
		  if(optional.isPresent())
			{
				Library library = optional.get();
				
				
				if(libraryDto.getLibraryName()!=null) {
				
				library.setLibraryName(libraryDto.getLibraryName());
				
				}

				if(libraryDto.getPhoneNumber()!=0) {
				
				library.setPhoneNumber(libraryDto.getPhoneNumber());
				
				}
				
				libraryRepository.save(library);
				apiResponse.setData(library);
				apiResponse.setMessage("Library Updated Successfully");
				apiResponse.setStatusCode(HttpStatus.OK.value());

				return new ResponseEntity<ApiResponse<Library>>(apiResponse, HttpStatus.OK);
				
			}
			else
			{
				throw new LibraryIdNotFoundException("Library Id Not Found");
			}
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> deleteLibraryById(int libraryId) {

		Optional<Library> optionalLibraryById = libraryRepository.findById(libraryId);
		if(optionalLibraryById.isEmpty())
		{
		  throw new LibraryIdNotFoundException("Library for this id not found");
		}

		Library library = optionalLibraryById.get();

		if (library.getBookList() != null && !library.getBookList().isEmpty()) {
			throw new IllegalStateException(
					"Cannot delete this library — it still has books assigned. Remove or reassign them first.");
		}


		libraryRepository.deleteById(libraryId);
		ApiResponse<Library> apiResponse = new ApiResponse<>();
		apiResponse.setData(null);
		apiResponse.setMessage(" Library deleted");
		apiResponse.setStatusCode(HttpStatus.OK.value()); 
		return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<Library>>> findAllLibrary() {
		List<Library> allibrary = libraryRepository.findAll();
		ApiResponse<List<Library>> apiResponse = new ApiResponse<>();
		apiResponse.setData(allibrary);
		apiResponse.setMessage("Address Fetched");
		apiResponse.setStatusCode(HttpStatus.OK.value()); 
		return new ResponseEntity<ApiResponse<List<Library>>>(apiResponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId, int bookId) {
		
		Optional<Library> optionalLibrary = libraryRepository.findById(libraryId);
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		
		ApiResponse<Library> apiResponse= new ApiResponse<>();
		
		if(optionalLibrary.isPresent() && optionalBook.isPresent())
		{
			Library library = optionalLibrary.get();
			Book book = optionalBook.get();
			
			List<Book> bookList = library.getBookList();
			
			if(bookList==null)
			{
				bookList = new ArrayList<>();
			}
			bookList.add(book);
			library.setBookList(bookList);
			
			libraryRepository.save(library);
			
			apiResponse.setData(library);
			apiResponse.setMessage("Book Added To Library Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value()); 
			return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);
			
			
		}
		else {
			 throw new BookUnableToAddToLibrary("LibraryId/BookId NotAvailable");
		}
		
		
	}

	@Override
	public ResponseEntity<ApiResponse<List<Book>>> displayBookFromLibrary(int libraryId) {
	   Optional<Library> OptionalLibrary = libraryRepository.findById(libraryId);	
	   ApiResponse<List<Book>> apiResponse= new ApiResponse<>();
	   if(OptionalLibrary.isPresent())
	   {
		   Library library = OptionalLibrary.get();
		   List<Book> bookList = library.getBookList();
		   

			apiResponse.setData(bookList);
			apiResponse.setMessage("Books Fetched Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value()); 
			return new ResponseEntity<ApiResponse<List<Book>>>(apiResponse,HttpStatus.OK);
			
		  
	   }
	   else {
		  throw new LibraryIdNotFoundException("Library For This Id Not Found");
	   }
	  
	 
	}

	@Override
	public ResponseEntity<ApiResponse<Library>> removeBookFromLibrary(int libraryId, int bookId) {

		Optional<Library> optionalLibraryById = libraryRepository.findById(libraryId);
		Optional<Book> optionalBookById = bookRepository.findById(bookId);

		if(optionalBookById.isEmpty())
		{
			throw new BookIdNotFoundException("book for id not found");
		}
		if(optionalLibraryById.isEmpty())
		{
			throw new LibraryIdNotFoundException("Library for this id not found");
		}
		Book book = optionalBookById.get();
		Library library = optionalLibraryById.get();

		List<Book> bookList = library.getBookList();
		if(bookList !=null && bookList.contains(book))
		{
			bookList.remove(book);
			libraryRepository.save(library);
		}
		else{
			throw new BookIdNotFoundException("Book for this id not found in library");
		}

		ApiResponse<Library> apiResponse=new ApiResponse();
		apiResponse.setData(null);
		apiResponse.setMessage("Books removed Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse<Library>>(apiResponse,HttpStatus.OK);
	}


}
	

