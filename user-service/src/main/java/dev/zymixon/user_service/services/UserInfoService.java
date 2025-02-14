package dev.zymixon.user_service.services;

import dev.zymixon.user_service.controllers.UserController;
import dev.zymixon.user_service.entities.UserInfo;
import dev.zymixon.user_service.exceptions.UserAlreadyExistsException;
import dev.zymixon.user_service.repositories.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);


    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo getUserByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    @Transactional
    public UserInfo createUser(UserInfo userInfo) {
        // Check if username or email already exists in one query
        Optional<UserInfo> existingUser = userInfoRepository.findByUsernameOrEmail(userInfo.getUsername(), userInfo.getEmail());

        if (existingUser.isPresent()) {
            String message = existingUser.get().getUsername().equals(userInfo.getUsername())
                    ? "Username already exists"
                    : "Email already taken";

            logger.warn(message);
            throw new UserAlreadyExistsException(message);
        }

        // Encrypt password
        userInfo.setPassword(PasswordUtil.hashPassword(userInfo.getPassword()));

        // Save and return user
        return userInfoRepository.save(userInfo);
    }
}
