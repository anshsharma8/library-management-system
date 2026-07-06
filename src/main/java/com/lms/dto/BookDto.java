package com.lms.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookDto {

	private int bookId;
	private String title;
	private String author;
	private boolean borrowed;
	private LocalDateTime borrowedTime;
	private LocalDateTime returnTime;
}
