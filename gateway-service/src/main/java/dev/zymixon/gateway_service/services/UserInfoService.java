package dev.zymixon.gateway_service.services;

import dev.zymixon.gateway_service.entity.UserInfo;
import dev.zymixon.gateway_service.exceptions.UnauthorizedException;
import dev.zymixon.gateway_service.exceptions.UserNotFoundException;
import dev.zymixon.gateway_service.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo findUserByUsername(String username) throws UserNotFoundException {
        return userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("Authorization failed"));
    }
}
