package com.ShooglooNetwork.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShooglooNetwork.Repository.UserDetailsRepository;
import com.ShooglooNetwork.model.UserDetails;

@RestController
@RequestMapping("/user")
public class UserDetailsController {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@CrossOrigin
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody UserDetails details) {
		String mail = details.getEmail();
		UserDetails user = new UserDetails();
		Optional<UserDetails> data =userDetailsRepository.findByEmail(mail); 
		if(data.isPresent()) {
			UserDetails existUser = data.get();
			if(data.get().isEmail_marketing()==true) {
				return new ResponseEntity<>("Already Exist",HttpStatus.CREATED);
			}else {
				user.setId(existUser.getId());
				user.setFirstname(existUser.getFirstname());
				user.setLastname(existUser.getLastname());
				user.setEmail(existUser.getEmail());
				user.setContent(existUser.getContent());
				user.setPhone(existUser.getPhone());
				user.setEmail_marketing(true);
				userDetailsRepository.save(user);
				return new ResponseEntity<>("added successfully",HttpStatus.OK);
			}
		}	
		else {
			user.setFirstname(details.getFirstname());
			user.setLastname(details.getLastname());
			user.setEmail(details.getEmail());
			user.setContent(details.getContent());
			user.setPhone(details.getPhone());
			user.setEmail_marketing(true);
			userDetailsRepository.save(user);
			return new ResponseEntity<>("added successfully",HttpStatus.OK);
			}
}	
		
	@CrossOrigin
	@PostMapping("/contact")
	public ResponseEntity<String> saveContact(@RequestBody UserDetails details) {
		String mail = details.getEmail();
		UserDetails user = new UserDetails();
		Optional<UserDetails> data =userDetailsRepository.findByEmail(mail); 
		if(data.isPresent()) {
			if(data.get().getPhone()!=null) {
			return new ResponseEntity<>("Already Exist",HttpStatus.CREATED); 
			}else {
			user.setId(data.get().getId());	
			user.setFirstname(details.getFirstname());
			user.setLastname(details.getLastname());
			user.setEmail(details.getEmail());
			user.setContent(details.getContent());
			user.setPhone(details.getPhone());
			userDetailsRepository.save(user);
			return new ResponseEntity<>("added successfully",HttpStatus.OK);
			}
		}
		else {
		user.setFirstname(details.getFirstname());
		user.setLastname(details.getLastname());
		user.setEmail(details.getEmail());
		user.setContent(details.getContent());
		user.setPhone(details.getPhone());
		userDetailsRepository.save(user);
		return new ResponseEntity<>("added successfully",HttpStatus.OK);
		}
		
	}

}
