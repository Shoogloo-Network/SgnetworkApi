package com.ShooglooNetwork.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public class SignUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private boolean conditions=true;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	private LocalDateTime updationDate;
	private String token;
	private Long expiredTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isConditions() {
		return conditions;
	}
	public void setConditions(boolean conditions) {
		this.conditions = conditions;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public LocalDateTime getUpdationDate() {
		return updationDate;
	}
	public void setUpdationDate(LocalDateTime updationDate) {
		this.updationDate = updationDate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Long getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Long expiredTime) {
		this.expiredTime = expiredTime;
	}
	
	@Override
	public String toString() {
		return "SignUp [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", conditions=" + conditions
				+ ", creationDate=" + creationDate + ", updationDate=" + updationDate + ", token=" + token
				+ ", expiredTime=" + expiredTime + "]";
	}
	
	
	

}
