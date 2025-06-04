package dev.zymixon.user_service.services;

import dev.zymixon.user_service.entities.UserRole;
import dev.zymixon.user_service.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole findByRole(String role) {
        return userRoleRepository.findRoleByName(role);
    }

}
