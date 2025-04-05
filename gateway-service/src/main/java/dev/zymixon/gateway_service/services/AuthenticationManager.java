package dev.zymixon.gateway_service.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);


    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        System.out.println("AuthenticationManager -> authenticate() is running");

        String token = authentication.getCredentials().toString();

        try {
            String username = jwtUtil.extractUsername(token);
            Claims claims = jwtUtil.extractClaim(token, c -> c);

            if (jwtUtil.validateToken(token)) {
                System.out.println("Token is valid");
                List<String> roles = claims.get("roles", List.class);
                if (roles == null) {
                    roles = new ArrayList<>(); // Default to empty list
                }

                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());


                return Mono.just(new UsernamePasswordAuthenticationToken(username, null, authorities));
            }
        } catch (ExpiredJwtException e) {
            // Handle the case when the token is expired
            logger.error("JWT expired: {}", e.getMessage());

            return Mono.empty();  // You can choose how you want to handle it (e.g., return Mono.empty() or throw a custom exception)
        } catch (Exception e) {
            // General error handling
            logger.error("Authentication error: {}", e.getMessage(), e);
            return Mono.empty(); // Invalid token or any other error
        }
        return Mono.empty();
    }


}
