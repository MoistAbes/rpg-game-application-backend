package dev.zymixon.user_service.controllers;

import dev.zymixon.user_service.entities.UserInfo;
import dev.zymixon.user_service.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @PostMapping("/register")
    public UserInfo register(@RequestBody UserInfo userInfo) {
        logger.info("users/register/ {}", userInfo);

        return userInfoService.createUser(userInfo);
    }

    @GetMapping("/get-all")
    public List<UserInfo> getAll() {
        logger.info("users/get-all");

        return userInfoService.getAllUsers();
    }


}
