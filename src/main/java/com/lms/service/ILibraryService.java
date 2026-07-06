package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.BookDto;
import com.lms.dto.LibraryDto;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.util.ApiResponse;

public interface ILibraryService  {
  
	public ResponseEntity<ApiResponse<Library>> saveLibrary(LibraryDto libraryDto,int addressId);
	public ResponseEntity<ApiResponse<Library>> findLibraryById(int libraryId);
	public ResponseEntity<ApiResponse<Library>>updateLibraryComplete(LibraryDto libraryDto);
	public ResponseEntity<ApiResponse<Library>>updateLibraryPartially(LibraryDto libraryDto,int libraryId);
	public ResponseEntity<ApiResponse<Library>>deleteLibraryById(int libraryId);
	public ResponseEntity<ApiResponse<List<Library>>> findAllLibrary();
	public ResponseEntity<ApiResponse<Library>> addBookToLibrary(int libraryId, int bookId);
	public ResponseEntity<ApiResponse<List<Book>>> displayBookFromLibrary(int libraryId);
	
	
}
