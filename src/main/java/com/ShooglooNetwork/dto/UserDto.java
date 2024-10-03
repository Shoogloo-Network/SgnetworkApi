package com.ShooglooNetwork.dto;

import java.util.Date;

public class UserDto {
	private String email;
	private String password;
	private String token;
	private String expiredTime;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", token=" + token + ", expiredTime=" + expiredTime
				+ "]";
	}
	
	

}
