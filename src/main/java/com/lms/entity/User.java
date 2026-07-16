package com.lms.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="user_details")
public class User {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private long phoneNumber;
	private String email;
	@JsonIgnore
	@Pattern(
			regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
			message = "Password must be at least 8 characters long and contain an uppercase letter, lowercase letter, digit, and special character."
	)
	private String password;
	private String role;
	@OneToOne
	private Address address;
	// "LOCAL" for username/password signups, "GOOGLE" for OAuth2 signups
	private String provider = "LOCAL";
}
