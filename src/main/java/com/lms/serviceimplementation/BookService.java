package com.lms.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.exception.BookIdNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.service.IBookService;
import com.lms.util.ApiResponse;
@Service
public class BookService implements IBookService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BookRepository bookRepository;
	
	
	@Override
	public ResponseEntity<ApiResponse<Book>> saveBook(BookDto BookDto) {

		Book book = modelMapper.map(BookDto,Book.class);
		bookRepository.save(book);
		
		ApiResponse<Book> apiResponse = new ApiResponse<>();
		apiResponse.setData(book);
		apiResponse.setMessage("Book Saved Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> findBookById(int bookId) {
		// TODO Auto-generated method stub
		Optional<Book> optional = bookRepository.findById(bookId);
	      ApiResponse<Book> apiResponse = new ApiResponse<>();
		     if(optional.isPresent())
		     {
		      Book book =optional.get();

				
				apiResponse.setData(book);
				apiResponse.setMessage("Book Found Successfully");
				apiResponse.setStatusCode(HttpStatus.OK.value()); // 200 ok
				return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.OK);// matching postman and Apiresonse
		      
		     }
		     else
		     {
                  throw new BookIdNotFoundException("Invalid Book Id");
			 }
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> updateBookComplete(BookDto bookDto) {
		// TODO Auto-generated method stub
		ApiResponse<Book> apiResponse = new ApiResponse<>();
		   Book book = modelMapper.map(bookDto, Book.class);
		   bookRepository.save(book);
		   
		    apiResponse.setData(book);
			apiResponse.setMessage("Book Updated");
			apiResponse.setStatusCode(HttpStatus.OK.value()); 
			return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.OK);
	}
	

	@Override
	public ResponseEntity<ApiResponse<Book>> updateBookPartially(BookDto bookDto, int bookId) {
		  Optional<Book> optional = bookRepository.findById(bookId);
		  ApiResponse<Book> apiResponse = new ApiResponse<>();
		  
		  if(optional.isPresent())
			{
				Book book = optional.get();
				
				
				if(bookDto.getAuthor()!=null) {
				
				book.setAuthor(bookDto.getAuthor());
				
				}
				if(bookDto.getTitle()!=null) {
					
					book.setTitle(bookDto.getTitle());
					
				}
				
				bookRepository.save(book);
				
				
				apiResponse.setData(book);
				apiResponse.setMessage("Book Updated Successfully");
				apiResponse.setStatusCode(HttpStatus.OK.value());

				return new ResponseEntity<ApiResponse<Book>>(apiResponse, HttpStatus.OK);
				
			}
			else
			{
				throw new BookIdNotFoundException("Book Id Not Found");
			}
		  }


	@Override
	public ResponseEntity<ApiResponse<Book>> deleteBookById(int bookId) {
		bookRepository.deleteById(bookId);
		ApiResponse<Book> apiResponse = new ApiResponse<>();
		apiResponse.setData(null);
		apiResponse.setMessage("Book Deleted");
		apiResponse.setStatusCode(HttpStatus.OK.value()); 
		return new ResponseEntity<ApiResponse<Book>>(apiResponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<Book>>> findAllBooks() {
		List<Book> allBook = bookRepository.findAll();
		ApiResponse<List<Book>> apiResponse = new ApiResponse<>();
		apiResponse.setData(allBook);
		apiResponse.setMessage("Address Fetched");
		apiResponse.setStatusCode(HttpStatus.OK.value()); 
		return new ResponseEntity<ApiResponse<List<Book>>>(apiResponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author) {
		
		
		List<Book> bookList = bookRepository.findByAuthor(author);	
		List<BookResponse> bookResponseList=new ArrayList<>();
		for(Book book:bookList)
		{
			bookResponseList.add(modelMapper.map(book, BookResponse.class));
		}
		ApiResponse<List<BookResponse>> apiResponse = new ApiResponse<>();
		apiResponse.setData(bookResponseList);
		apiResponse.setMessage("Address Fetched Based On Author Successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value()); 
		return new ResponseEntity<ApiResponse<List<BookResponse>>>(apiResponse,HttpStatus.OK);
		
		
		
	}
	

}
