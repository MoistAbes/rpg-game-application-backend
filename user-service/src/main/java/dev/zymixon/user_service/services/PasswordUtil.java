package dev.zymixon.user_service.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static boolean passwordMatches(String storedPasswordHash, String enteredPassword) {
        return passwordEncoder.matches(enteredPassword, storedPasswordHash);
    }

    public static String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
