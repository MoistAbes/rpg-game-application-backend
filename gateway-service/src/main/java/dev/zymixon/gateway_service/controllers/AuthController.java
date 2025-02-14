package dev.zymixon.gateway_service.controllers;

import dev.zymixon.gateway_service.entity.UserInfo;
import dev.zymixon.gateway_service.model.LoginRequest;
import dev.zymixon.gateway_service.repository.UserInfoRepository;
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

    private final JwtUtil jwtUtil;
    private final UserInfoRepository userInfoRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    public AuthController(JwtUtil jwtUtil, UserInfoRepository userInfoRepository) {
        this.jwtUtil = jwtUtil;
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest loginRequest) {
        logger.info("/auth/login | username: {}, password: {}", loginRequest.getUsername(), loginRequest.getPassword());

        UserInfo userInfo = userInfoRepository.findByUsername(loginRequest.getUsername());

        //my version
        if (userInfo != null) {
            if (passwordMatches(userInfo.getPassword(), loginRequest.getPassword())) {
                String token = jwtUtil.generateToken(userInfo.getUsername());
                return Mono.just(ResponseEntity.ok("Bearer " + token));
            }else {
                return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
            }
        }else {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
        }

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

    private boolean passwordMatches(String storedPassword, String enteredPassword) {
        // Add password checking logic (e.g., use BCrypt for password comparison)
        return storedPassword.equals(enteredPassword);
    }

}
