package com.ShooglooNetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShooglooNetwork.dto.ApiResponse;
import com.ShooglooNetwork.service.OtpService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/otp")
public class OtpRecordController {
	
	
	@Autowired
    private OtpService otpService;

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse> generateOTP(@RequestParam String email) {
      String otp =otpService.generateAndSendOTP(email);
      ApiResponse response = new ApiResponse();
      response.setPayload("OTP sent to " + email+" "+"otp is "+otp);
      response.setStatus(""+HttpStatus.OK);
      response.setError(null);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verifyOTP(@RequestParam String email, @RequestParam String otp) {
    	ApiResponse response = new ApiResponse();
        if (otpService.verifyOTP(email, otp)) {
        	response.setPayload("OTP verified successfully");
        	response.setStatus(""+HttpStatus.OK);
            return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
        } else {
        	response.setPayload("Invalid or expired OTP");
        	response.setStatus(""+HttpStatus.OK);
            return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
        }
    }
}
