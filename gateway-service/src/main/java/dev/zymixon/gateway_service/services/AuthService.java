package dev.zymixon.gateway_service.services;

import dev.zymixon.gateway_service.controllers.AuthController;
import dev.zymixon.gateway_service.entity.UserInfo;
import dev.zymixon.gateway_service.exceptions.UserNotFoundException;
import dev.zymixon.gateway_service.model.LoginRequest;
import dev.zymixon.gateway_service.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

//    private final UserInfoRepository userInfoRepository;
    private final UserInfoService userInfoService;
    private final JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);


    public AuthService(UserInfoService userInfoService, JwtUtil jwtUtil) {
        this.userInfoService = userInfoService;
        this.jwtUtil = jwtUtil;
    }


    public Mono<ResponseEntity<String>> verifyCredentials(LoginRequest loginRequest) throws UserNotFoundException {

        UserInfo userInfo = userInfoService.findUserByUsername(loginRequest.getUsername());

        if (PasswordUtil.passwordMatches(userInfo.getPassword(), loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(userInfo.getUsername(), userInfo.getId());
            return Mono.just(ResponseEntity.ok("Bearer " + token));
        }else {
            logger.error("Invalid password");
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password"));
        }


    }

//    private boolean passwordMatches(String storedPassword, String enteredPassword) {
//        // Add password checking logic (e.g., use BCrypt for password comparison)
//        return storedPassword.equals(enteredPassword);
//    }
}
