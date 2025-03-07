package dev.zymixon.gateway_service.controllers;

import dev.zymixon.gateway_service.dto.JwtTokenDto;
import dev.zymixon.gateway_service.exceptions.UserNotFoundException;
import dev.zymixon.gateway_service.model.LoginRequest;
import dev.zymixon.gateway_service.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<JwtTokenDto>> login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        logger.info("/auth/login | username: {}, password: {}", loginRequest.getUsername(), loginRequest.getPassword());

        return authService.verifyCredentials(loginRequest);

    }
//    @PostMapping("/login/{username}/{password}")
//    public Mono<ResponseEntity<String>> login(@PathVariable String username, @PathVariable String password) throws UserNotFoundException {
////        logger.info("/auth/login | username: {}, password: {}", loginRequest.getUsername(), loginRequest.getPassword());
//        logger.info("/auth/login | username: {}, password: {}", username, password);
//
////        return authService.verifyCredentials(loginRequest);
//
//
//        return Mono.just(ResponseEntity.ok("Bearer " + "lol"));
//    }



}
