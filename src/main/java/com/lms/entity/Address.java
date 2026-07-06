package com.lms.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_id_generator")
	@SequenceGenerator(name="address_id_generator",initialValue=101,allocationSize=1)
	
	private int addressId;
	private int houseNumber;
	private String area;
	private String city;
	private String state;
	private String country;
	private long pincode;
	
	
}
