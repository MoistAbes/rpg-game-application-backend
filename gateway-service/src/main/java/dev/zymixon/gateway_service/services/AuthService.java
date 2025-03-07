package dev.zymixon.gateway_service.services;

import dev.zymixon.gateway_service.dto.JwtTokenDto;
import dev.zymixon.gateway_service.entity.UserInfo;
import dev.zymixon.gateway_service.exceptions.UserNotFoundException;
import dev.zymixon.gateway_service.model.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    public Mono<ResponseEntity<JwtTokenDto>> verifyCredentials(LoginRequest loginRequest) throws UserNotFoundException {

        UserInfo userInfo = userInfoService.findUserByUsername(loginRequest.getUsername());

        if (PasswordUtil.passwordMatches(userInfo.getPassword(), loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(userInfo.getUsername(), userInfo.getId());
            System.out.println("Sending token: " + token);
//            return Mono.just(ResponseEntity.ok("Bearer " + token));
            return Mono.just(ResponseEntity.ok(JwtTokenDto.builder().token("Bearer " + token).build()));
        }else {
            logger.error("Invalid password");
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JwtTokenDto.builder().token("Invalid username or password").build()));
        }


    }

//    private boolean passwordMatches(String storedPassword, String enteredPassword) {
//        // Add password checking logic (e.g., use BCrypt for password comparison)
//        return storedPassword.equals(enteredPassword);
//    }
}
