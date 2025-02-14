package dev.zymixon.gateway_service.controllers;

import dev.zymixon.gateway_service.entity.UserInfo;
import dev.zymixon.gateway_service.exceptions.UserNotFoundException;
import dev.zymixon.gateway_service.model.LoginRequest;
import dev.zymixon.gateway_service.repository.UserInfoRepository;
import dev.zymixon.gateway_service.services.AuthService;
import dev.zymixon.gateway_service.services.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        logger.info("/auth/login | username: {}, password: {}", loginRequest.getUsername(), loginRequest.getPassword());

        return authService.verifyCredentials(loginRequest);

        //ai version
//        return userInfoRepository.findByUsername(loginRequest.getUsername())
//                .flatMap(user -> {
//                    // Check if password is correct
//                    if (passwordMatches(user.getPassword(), loginRequest.getPassword())) {
//                        // Generate token
//                        String token = jwtUtil.generateToken(user.getUsername());
//                        return Mono.just(ResponseEntity.ok("Bearer " + token));
//                    } else {
//                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
//                    }
//                })
//                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")));
    }



}
