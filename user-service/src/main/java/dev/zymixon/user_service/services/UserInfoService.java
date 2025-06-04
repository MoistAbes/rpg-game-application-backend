package dev.zymixon.user_service.services;


import dev.zymixon.user_service.entities.UserInfo;
import dev.zymixon.user_service.exceptions.UserAlreadyExistsException;
import dev.zymixon.user_service.repositories.UserInfoRepository;
import dev.zymixon.user_service.repositories.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserRoleRepository userRoleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);



    public UserInfoService(UserInfoRepository userInfoRepository, UserRoleRepository userRoleRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRoleRepository = userRoleRepository;
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

        //set user roles
        userInfo.setRoles(Set.of(userRoleRepository.findRoleByName("ROLE_USER")));

        // Save and return user
        return userInfoRepository.save(userInfo);
    }


    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }
}
