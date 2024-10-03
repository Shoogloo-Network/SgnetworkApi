package com.ShooglooNetwork.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ShooglooNetwork.Repository.SignUpRepository;
import com.ShooglooNetwork.dto.ApiResponse;
import com.ShooglooNetwork.dto.UserDto;
import com.ShooglooNetwork.model.SignUp;

@Service
public class SignUpService {
	
	@Autowired
	SignUpRepository signUpRepository;
	@Autowired
	private JwtUtil jwtUtil;
	
	
	public ResponseEntity<ApiResponse> signup(@RequestBody SignUp signup){
		UserDto userDto = new UserDto();
		ApiResponse response = new ApiResponse();
		userDto.setEmail(signup.getEmail());
		
		SignUp newUser = signUpRepository.findByEmail(signup.getEmail());
		if(newUser!=null) {
			 response.setPayload(userDto);
			 response.setStatus(""+HttpStatus.CREATED);
			 response.setStatusCode(201);
			 response.setError(null);
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			String token=jwtUtil.generateToken(signup.getEmail());
			Date date = jwtUtil.getExpirationDateFromToken(token);
			signup.setToken(token);
			userDto.setToken(token);
			userDto.setExpiredTime(""+date);
		signUpRepository.save(signup);
		 response.setPayload(userDto);
		 response.setStatus(""+HttpStatus.OK);
		 response.setStatusCode(200);
		 response.setError(null);
		return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	public ResponseEntity<ApiResponse> signin(@RequestBody UserDto userDto){
		SignUp newUser = signUpRepository.findByEmailAndPassword(userDto.getEmail(),userDto.getPassword()); 
		ApiResponse response = new ApiResponse();
		if(newUser!=null) {
			userDto.setPassword("");
			userDto.setToken(newUser.getToken());
			//System.out.println("token expired"+jwtUtil.isTokenExpired(newUser.getToken()));
			 if(jwtUtil.isTokenExpired(newUser.getToken())==true) {
			    	String token=jwtUtil.generateToken(userDto.getEmail());
			    	Date date = jwtUtil.getExpirationDateFromToken(token);
			    	newUser.setToken(token);
			    	signUpRepository.save(newUser);
			    	userDto.setToken(token);
			    	userDto.setExpiredTime(""+date);
			    }
			 response.setPayload(userDto);
			 response.setStatus(""+HttpStatus.OK);
			 response.setStatusCode(200);
			 response.setError(null);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			 response.setPayload(userDto);
			 response.setStatus(""+HttpStatus.NOT_FOUND);
			 response.setStatusCode(404);
			 response.setError(null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<UserDto> updateToen(@RequestHeader String token){
		SignUp newUser = signUpRepository.findByToken(token); 
		UserDto userDto = new UserDto();
		if(newUser!=null) {
			userDto.setEmail(jwtUtil.extractUsername(token));
			userDto.setPassword("");
			userDto.setToken(newUser.getToken());
			//System.out.println("token expired"+jwtUtil.isTokenExpired(newUser.getToken()));
			 if(jwtUtil.isTokenExpired(newUser.getToken())==true) {
			    	String updatetoken=jwtUtil.generateToken(newUser.getEmail());
			    	newUser.setToken(updatetoken);
			    	signUpRepository.save(newUser);
			    	Date date = jwtUtil.getExpirationDateFromToken(token);
			    	userDto.setExpiredTime(""+date);
			    	userDto.setToken(updatetoken);
			    }
			return new ResponseEntity<>(userDto,HttpStatus.OK);
		}else {
		return new ResponseEntity<>(userDto,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ApiResponse> updateProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
		ApiResponse response = new ApiResponse();
	    Optional<SignUp> existingUserOpt = signUpRepository.findById(id);
	    if (existingUserOpt.isPresent()) {
	        SignUp existingUser = existingUserOpt.get();
	        
	        updates.forEach((key, value) -> {
	            switch (key) {
	                case "firstName":
	                    if (value != null) {
	                        existingUser.setFirstName((String) value);
	                    }
	                    break;
	                case "lastName":
	                    if (value != null) {
	                        existingUser.setLastName((String) value);
	                    }
	                    break;
	                case "email":
	                    if (value != null) {
	                        existingUser.setEmail((String) value);
	                    }
	                    break;
	                case "phoneNumber":
	                    if (value != null) {
	                        existingUser.setPhoneNumber((String) value);
	                    }
	                    break;
	                default:
	                    System.out.println("Unexpected key: " + key);
	                    break;
	            }
	        });

	        signUpRepository.save(existingUser);
	        response.setPayload("updated successfully");
	        response.setStatusCode(200);
	        response.setStatus(""+HttpStatus.OK);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	    	response.setPayload("failed to update");
	    	 response.setStatusCode(404);
		        response.setStatus(""+HttpStatus.NOT_FOUND);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}
	public ResponseEntity<ApiResponse> UpdatePassword(@PathVariable String email, @RequestBody Map<String, Object> updates) {
		ApiResponse response = new ApiResponse();
	    SignUp existingUser = signUpRepository.findByEmail(email);
	    if (existingUser!=null) {
	      //  SignUp existingUser = existingUserOpt.get();
	        
	        updates.forEach((key, value) -> {
	            switch (key) {
	                case "password":
	                    if (value != null) {
	                        existingUser.setPassword((String) value);
	                    }
	                    break;
	  	                default:
	                    System.out.println("Unexpected key: " + key);
	                    break;
	            }
	        });

	        signUpRepository.save(existingUser);
	        response.setPayload("updated successfully");
	        response.setStatusCode(200);
	        response.setStatus(""+HttpStatus.OK);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	    	response.setPayload("failed to update");
	    	 response.setStatusCode(404);
		        response.setStatus(""+HttpStatus.NOT_FOUND);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}

}
