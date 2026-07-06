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

import com.lms.dto.BookDto;
import com.lms.dto.LibraryDto;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.serviceimplementation.LibraryService;
import com.lms.util.ApiResponse;



@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	LibraryService libraryService;
	
	@PostMapping("{addressId}")
	public ResponseEntity< ApiResponse<Library>> saveAddress(@RequestBody LibraryDto libraryDto,@PathVariable int addressId)
	{
		return libraryService.saveLibrary(libraryDto,addressId);
	}
	

	@GetMapping("{libraryId}")
	public ResponseEntity<ApiResponse<Library>> findLibraryById(@PathVariable int libraryId)
	{
		return libraryService.findLibraryById(libraryId);
	}
	

	@PutMapping
	public  ResponseEntity<ApiResponse<Library>> updateLibraryomplete(@RequestBody LibraryDto libraryDto)
	{
		return libraryService.updateLibraryComplete(libraryDto);
	}
	@PatchMapping("/{libraryId}")
	public  ResponseEntity<ApiResponse<Library>> updateLibraryPartially(@RequestBody LibraryDto libraryDto,@PathVariable int libraryId)
	{
		return libraryService.updateLibraryPartially(libraryDto,libraryId);
	}
	
	@DeleteMapping("{libraryId}")
	public  ResponseEntity<ApiResponse<Library>> deleteLibraryById(@PathVariable int libraryId)
	{
		return libraryService.deleteLibraryById(libraryId);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<ApiResponse<List<Library>>> findAllLibrary()
	{
		return libraryService.findAllLibrary();
	}
	
	@PutMapping("/{libraryId}/{bookId}")
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(@PathVariable int libraryId,@PathVariable int bookId)
	{
		return libraryService.addBookToLibrary(libraryId, bookId);
	}
	
	@GetMapping("/displayBooks/{libraryId}")
	public ResponseEntity<ApiResponse<List<Book>>> displayBookFromLibrary(@PathVariable int libraryId) {
		
		return libraryService.displayBookFromLibrary(libraryId);
	}
}
