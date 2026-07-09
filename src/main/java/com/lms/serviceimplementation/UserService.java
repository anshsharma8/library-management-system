package com.lms.serviceimplementation;

import com.lms.dto.LoginRequestDto;
import com.lms.dto.LoginResponseDto;
import com.lms.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.lms.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.Book;
import com.lms.entity.User;
import com.lms.exception.BookIdNotFoundException;
import com.lms.exception.BookUnableToBorrowException;
import com.lms.exception.UserIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.UserRepository;
import com.lms.service.IUserService;
import com.lms.util.ApiResponse;
@Service

public class UserService implements IUserService, UserDetailsService {


	@Autowired
	
	ModelMapper modelMapper;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	BookRepository bookRepository;

	@Lazy
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public ResponseEntity<ApiResponse<User>> registerUser(UserDto userDto, int addressId) {

		User user = modelMapper.map(userDto, User.class);

		Optional<Address> optional = addressRepository.findById(addressId);

		ApiResponse<User> apiResponse = new ApiResponse<>();

		if (optional.isPresent()) {

			Address address = optional.get();

			user.setAddress(address);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(user);

			apiResponse.setData(user);
			apiResponse.setMessage("User saved Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		}

		else {

			apiResponse.setData(null);
			apiResponse.setMessage("Address For This Id Not Found, User Failed To Save");
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ApiResponse<User>> findUserById(int userId) {

		Optional<User> optional = userRepository.findById(userId);

		ApiResponse<User> apiResponse = new ApiResponse<>();

		if (optional.isPresent()) {

			User user = optional.get();

			apiResponse.setData(user);
			apiResponse.setMessage("User Found Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
		}

		else {

			throw new UserIdNotFoundException("Invalid User Id");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<User>> updateUserComplete(UserDto userDto) {

		ApiResponse<User> apiResponse = new ApiResponse<>();

		User user = modelMapper.map(userDto, User.class);

		userRepository.save(user);

		apiResponse.setData(user);
		apiResponse.setMessage("User Updated");
		apiResponse.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ApiResponse<User>> updateUserPartially(UserDto userDto, int userId) {
		Optional<User> optional = userRepository.findById(userId);
		ApiResponse<User> apiResponse = new ApiResponse<>();
		
		
		if(optional.isPresent())
		{
			User user = optional.get();
			
			
			if(userDto.getUserName()!=null) {
			
			user.setUserName(userDto.getUserName());
			
			}
			
			if(userDto.getEmail()!=null) {
				
				user.setEmail(userDto.getEmail());
				
			}

			if(userDto.getPhoneNumber()!=0) {
				
				user.setPhoneNumber(userDto.getPhoneNumber());
			}
			userRepository.save(user);
			apiResponse.setData(user);
			apiResponse.setMessage("User Updated Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
			
		}
		else
		{
			throw new UserIdNotFoundException("User Id Not Found");
		}
	}
	
	
	

	@Override
	public ResponseEntity<ApiResponse<User>> deleteUserById(int userId) {

		userRepository.deleteById(userId);

		ApiResponse<User> apiResponse = new ApiResponse<>();

		apiResponse.setData(null);
		apiResponse.setMessage("User deleted");
		apiResponse.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<List<User>>> findAllUser() {

		List<User> allUser = userRepository.findAll();

		ApiResponse<List<User>> apiResponse = new ApiResponse<>();

		apiResponse.setData(allUser);
		apiResponse.setMessage("Users Fetched");
		apiResponse.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ApiResponse<List<User>>>(apiResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<User>> borrowBookByUser(int userId, int bookId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		
		if (optionalUser.isPresent() && optionalBook.isPresent() && !optionalBook.get().isBorrowed()) {
			
			User user = optionalUser.get();
			Book book = optionalBook.get();
			book.setUser(user);
			book.setBorrowedTime(LocalDateTime.now());
			book.setBorrowed(true);
			
			bookRepository.save(book);
			

			ApiResponse<User> apiResponse = new ApiResponse<>();

			apiResponse.setData(user);
			apiResponse.setMessage("Users Borrowed Book Successfully");
			apiResponse.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
			
			
		} else {
            throw new BookUnableToBorrowException("invalid bookid/userid or book is already borrowed");
		}
	}

	@Override
	public ResponseEntity<ApiResponse<User>> returnBook(int bookId) {
		 Optional<Book> optionalBook = bookRepository.findById(bookId);
		 
		 if(optionalBook.isPresent())
		 {
			 Book book = optionalBook.get();
			 book.setBorrowed(false);
			 book.setReturnTime(LocalDateTime.now());
			 book.setUser(null);
			 
			 bookRepository.save(book);
			 

				ApiResponse<User> apiResponse = new ApiResponse<>();

				apiResponse.setData(null);
				apiResponse.setMessage("User Returned Book Successfully");
				apiResponse.setStatusCode(HttpStatus.OK.value());

				return new ResponseEntity<ApiResponse<User>>(apiResponse, HttpStatus.OK);
				
				
			} else {
	           throw new BookIdNotFoundException("Book For This Id Not Found");
			 
		 }
	}

	@Override
	public ResponseEntity<ApiResponse<LoginResponseDto>> login(LoginRequestDto loginRequestDto) {

		// Step 1 — verify credentials
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));

		// Step 2 — get user from DB
		Optional<User> optionalUser = userRepository.findByEmail(loginRequestDto.getEmail());

		if (optionalUser.isEmpty())
		{
			throw new UsernameNotFoundException("User Not Found");
		}

		User user= optionalUser.get();
        // step 3 token generation.
		String token= jwtUtil.generateToken(user.getEmail(), user.getRole(),user.getUserId());

		ApiResponse<LoginResponseDto> apiResponse = new ApiResponse<>();
		apiResponse.setData(new LoginResponseDto(token));
		apiResponse.setMessage("Login Successful");
		apiResponse.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(apiResponse, HttpStatus.OK);

	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("user not found" + email);
		}
		User userByEmail = optionalUser.get();

		String username = userByEmail.getEmail();
		String password = userByEmail.getPassword();
		String role = userByEmail.getRole();


		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

		List<SimpleGrantedAuthority> authorities = List.of(authority);

		return new org.springframework.security.core.userdetails.User(username, password, authorities);


	}
}
