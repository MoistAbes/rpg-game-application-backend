package dev.zymixon.user_service.services;

import dev.zymixon.user_service.entities.UserInfo;
import dev.zymixon.user_service.repositories.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo getUserByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    public UserInfo createUser(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }
}
