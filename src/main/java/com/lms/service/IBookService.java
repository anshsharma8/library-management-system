package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.BookDto;
import com.lms.dto.BookResponse;
import com.lms.entity.Book;
import com.lms.util.ApiResponse;

public interface IBookService {
	public ResponseEntity<ApiResponse<Book>> saveBook(BookDto BookDto);
	public ResponseEntity<ApiResponse<Book>> findBookById(int bookId);
	public ResponseEntity<ApiResponse<Book>> updateBookComplete(BookDto bookDto);
	public ResponseEntity<ApiResponse<Book>> updateBookPartially(BookDto bookDto,int bookId);
	public ResponseEntity<ApiResponse<Book>> deleteBookById(int bookId);
	public ResponseEntity<ApiResponse<List<Book>>> findAllBooks();
	public ResponseEntity<ApiResponse<List<BookResponse>>> displayBookByAuthor(String author);
}
