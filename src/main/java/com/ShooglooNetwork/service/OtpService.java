package com.ShooglooNetwork.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShooglooNetwork.Repository.OtpRecordRepository;
import com.ShooglooNetwork.Repository.SignUpRepository;
import com.ShooglooNetwork.model.OtpRecord;
import com.ShooglooNetwork.model.SignUp;

@Service
public class OtpService {
	
	@Autowired
	OtpRecordRepository otpRecordRepository;
	@Autowired
	SignUpRepository signUpRepository;
	@Autowired
	JwtUtil jwtUtil;
		
	private static final int OTP_EXPIRY_MINUTES = 5;
	
	public String generateAndSendOTP(String email) {
        String otp = OtpGenerator.generateOTP();
        OtpRecord otpRecord = new OtpRecord();
       
        otpRecord.setEmail(email);
        otpRecord.setOtp(otp);
        otpRecord.setCreationDate(LocalDateTime.now());
        otpRecord.setExpiryDate(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
       
        otpRecordRepository.save(otpRecord);
       return otp;
       // sendEmail(email, otp);
    }
	public boolean verifyOTP(String email, String otp) {
        Optional<OtpRecord> otpRecordOptional = otpRecordRepository.findByEmail(email);
        if (otpRecordOptional.isPresent()) {
            OtpRecord otpRecord = otpRecordOptional.get();
            if (otpRecord.getOtp().equals(otp) && otpRecord.getExpiryDate().isAfter(LocalDateTime.now())) {
                otpRecordRepository.delete(otpRecord);
                SignUp user = new SignUp();
                String token=jwtUtil.generateToken(email);
                SignUp existingUser = signUpRepository.findByEmail(email);
                if (existingUser != null) {
                    // Update existing user
                	user.setId(existingUser.getId());
                	user.setFirstName(existingUser.getFirstName());
                	user.setLastName(existingUser.getLastName());
                	user.setConditions(true);
                	user.setPhoneNumber(existingUser.getPhoneNumber());
                	user.setEmail(existingUser.getEmail());
                	user.setToken(token);
                    signUpRepository.save(user);
                } else {
                    // Insert new user
                    user.setEmail(email);
                    user.setToken(token);
                    signUpRepository.save(user);
                }
                return true;
            }
        }
        return false;
    }

}
