package com.lms.controller;

import java.util.List;

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

import com.lms.dto.AddressDto;
import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.serviceimplementation.BookService;
import com.lms.util.ApiResponse;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping
	public ResponseEntity< ApiResponse<Book>> saveBook(@RequestBody BookDto bookDto)
	{
		return bookService.saveBook(bookDto);
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<ApiResponse<Book>> findBookById(@PathVariable int bookId)
	{
		return bookService.findBookById(bookId);
	}
	
	@PutMapping
	public  ResponseEntity<ApiResponse<Book>> updateBookComplete(@RequestBody BookDto bookDto)
	{
		return bookService.updateBookComplete(bookDto);
	}
	@PatchMapping("/{bookId}")
	public  ResponseEntity<ApiResponse<Book>> updateBookPartially(@RequestBody BookDto bookDto,@PathVariable int bookId)
	{
		return bookService.updateBookPartially(bookDto,bookId);
	}
	
	
	@DeleteMapping("/{bookId}")
	public  ResponseEntity<ApiResponse<Book>> deleteBookById(@PathVariable int bookId)
	{
		return bookService.deleteBookById(bookId);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ApiResponse<List<Book>>> findAllBooks()
	{
		return bookService.findAllBooks();
	}
	@GetMapping("/author/{author}")
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(@PathVariable String author)
	{
		return bookService.displayBookByAuthor(author);
	}
	
}
