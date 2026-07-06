package com.lms.dto;

import java.util.List;

import com.lms.entity.Address;
import com.lms.entity.Book;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LibraryDto {
	
	private int libraryId;
	private String libraryName;
	private long phoneNumber;
	
}
