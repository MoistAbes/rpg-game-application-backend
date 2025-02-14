package dev.zymixon.gateway_service.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println("Decoded Key (Base64): " + Base64.getEncoder().encodeToString(keyBytes));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(getSigningKey()) // Secure key
                .compact();
    }

    public boolean validateToken(String token) {



        System.out.println("Validating token");
        System.out.println(token);
        System.out.println("Expiration date: " + extractExpiration(token));
        System.out.println("Claims: " + extractClaim(token, Claims::getSubject));

        try {
            Jwts.parser()
                    .verifyWith(getSigningKey()) // Verify signature
                    .build()
                    .parseSignedClaims(token); // If parsing works, token is valid
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false; // Invalid token
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSigningKey()) // Updated parsing method
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        System.out.println("Checking if token is expired");
        return extractExpiration(token).before(new Date());
    }

}
