package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Book;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

	// create customized method
// start from findByNameOfDataMember
	
	
	List<Book> findByAuthor(String author);

	List<Book> findByTitle(String title);
	List<Book> findByAuthorAndTitle(String author,String title);

	//Book WHERE book.user.userId = ?
	List<Book> findByUser_UserId(int userId);
	
}
