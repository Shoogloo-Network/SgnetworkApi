package com.ShooglooNetwork.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShooglooNetwork.dto.ApiResponse;
import com.ShooglooNetwork.dto.UserDto;
import com.ShooglooNetwork.model.SignUp;
import com.ShooglooNetwork.service.SignUpService;


@RestController
@RequestMapping("/sign")

public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	@PostMapping("/up")
	public ResponseEntity<ApiResponse> signup(@RequestBody SignUp signup){
		return signUpService.signup(signup);
		
	}
	@PostMapping("/in")
	public ResponseEntity<ApiResponse> signin(@RequestBody UserDto userDto){
		return signUpService.signin(userDto);
		
	}
	@PostMapping("/updateToken")
	public ResponseEntity<UserDto> updateToen(@RequestHeader String token){
		return signUpService.updateToen(token);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
		return signUpService.updateProfile(id, updates);
	}
	@PatchMapping("/updatePassword/{email}")
	public ResponseEntity<ApiResponse> UpdatePassword(@PathVariable String email, @RequestBody Map<String, Object> updates) {
		return signUpService.UpdatePassword(email, updates);
	}
}
