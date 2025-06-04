package dev.zymixon.gateway_service.controllers;

import dev.zymixon.gateway_service.dto.JwtTokenDto;
import dev.zymixon.gateway_service.exceptions.UserNotFoundException;
import dev.zymixon.gateway_service.model.LoginRequest;
import dev.zymixon.gateway_service.services.AuthService;
import dev.zymixon.gateway_service.services.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;
    private final JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    public AuthController(AuthService authService, JwtUtil jwtUtil) {

        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<JwtTokenDto>> login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        logger.info("/auth/login | username: {}, password: {}", loginRequest.getUsername(), loginRequest.getPassword());

        return authService.verifyCredentials(loginRequest);

    }

    @PostMapping("/select-character/{characterId}")
    public Mono<ResponseEntity<JwtTokenDto>> selectCharacter(@PathVariable Long characterId, ServerHttpRequest request) {

        String authHeader = request.getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }

        String token = authHeader.substring(7); // Remove "Bearer "

        Long userId = jwtUtil.extractUserId(token);
        String username = jwtUtil.extractUsername(token);
        Set<String> roles = jwtUtil.extractRoles(token);

        System.out.println("extractUsername: " + username);
        System.out.println("extracting userId: " + userId);
        System.out.println("extracting characterId: " + characterId);

        String newToken = jwtUtil.generateToken(username, userId, characterId, roles);

        return Mono.just(ResponseEntity.ok(JwtTokenDto.builder().token("Bearer " + newToken).build()));
    }


}
