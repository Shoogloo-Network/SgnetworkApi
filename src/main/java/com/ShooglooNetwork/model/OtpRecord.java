package com.ShooglooNetwork.model;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class OtpRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String phoneNumber;
	private String otp;
	@CreationTimestamp
	private LocalDateTime CreationDate;
	private LocalDateTime ExpiryDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public LocalDateTime getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		CreationDate = creationDate;
	}
	public LocalDateTime getExpiryDate() {
		return ExpiryDate;
	}
	public void setExpiryDate(LocalDateTime expiryDate) {
		ExpiryDate = expiryDate;
	}
	@Override
	public String toString() {
		return "OtpRecord [id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber + ", otp=" + otp
				+ ", CreationDate=" + CreationDate + ", ExpiryDate=" + ExpiryDate + "]";
	}
	
}
