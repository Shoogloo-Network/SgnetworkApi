package com.ShooglooNetwork.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static String secretKey = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; // Replace with a strong secret key

    @SuppressWarnings("deprecation")
	public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*1)) // 1 hour validity
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        try { 
        	return extractClaims(token).getExpiration().before(new Date());
        }
        catch (ExpiredJwtException e) {
            // Token is expired
            return true;
    }
       
    }
    public boolean validateToken(String token, String email) {
        return (email.equals(extractUsername(token)) && !isTokenExpired(token));
    }
    public static  Date getExpirationDateFromToken(String token) {
        
		return Jwts.parser()
		        .setSigningKey(secretKey)
		        .parseClaimsJws(token)
		        .getBody()
		        .getExpiration();
	
}
}
