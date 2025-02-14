package dev.zymixon.user_service.controllers;

import dev.zymixon.user_service.entities.UserInfo;
import dev.zymixon.user_service.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/get-by-username/{username}")
    public ResponseEntity<UserInfo> getByUsername(@PathVariable String username) {
        logger.info("users/get-by-username/ {}", username);

        return ResponseEntity.ok(userInfoService.getUserByUsername(username));
    }


    @PostMapping("/register")
    public UserInfo register(@RequestBody UserInfo userInfo) {
        logger.info("users/register/ {}", userInfo.getUsername());

        return userInfoService.createUser(userInfo);
    }

}
