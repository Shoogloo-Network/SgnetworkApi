package com.ShooglooNetwork.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class OtpGenerator {
	private static final String CHARACTERS = "0123456789";
    private static final int OTP_LENGTH = 6;

    public static String generateOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return otp.toString();
    }
	
    

}
