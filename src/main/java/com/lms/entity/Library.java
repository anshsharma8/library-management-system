package com.lms.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "library_details")
public class Library {
    
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="library_id_generator")
	@SequenceGenerator(name="library_id_generator",initialValue=301,allocationSize = 1)
	private int libraryId;
	private String libraryName;
	@Column(unique=true)
	private long phoneNumber;
	@OneToOne
	private Address address;
	@OneToMany
	private List<Book> bookList;
	
}
