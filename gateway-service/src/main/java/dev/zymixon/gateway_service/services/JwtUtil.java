package dev.zymixon.gateway_service.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
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

    public String generateToken(String username, Long userId, Long characterId, Set<String> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("userId", userId)
                .claim("username", username)
                .claim("characterId", characterId)
                .claim("roles", roles) // 👈 Add roles claim
                .issuedAt(new Date())
                .expiration(setExpiration(12))
                .signWith(getSigningKey()) // Secure key
                .compact();
    }

    public boolean validateToken(String token) {
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

    //do wykorzystania pozniej
    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    public Set<String> extractRoles(String token) {
        return new HashSet<>(
                extractClaim(token, claims -> (List<String>) claims.get("roles"))
        );
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

    // Method to set expiration based on hours
    public static Date setExpiration(int hours) {
        // Ensure the multiplication happens in long to avoid integer overflow or casting issues
        long expirationTimeInMillis = 1000L * 60L * 60L * hours; // Explicitly use long for all the values involved
        return new Date(System.currentTimeMillis() + expirationTimeInMillis);  // Add the current time to get the future expiration time
    }



}
