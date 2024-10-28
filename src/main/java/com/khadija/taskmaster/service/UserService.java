package com.khadija.taskmaster.service;

import com.khadija.taskmaster.exception.UserAlreadyExistsException;
import com.khadija.taskmaster.model.User;
import com.khadija.taskmaster.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }
        if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
            String username = oauth2Token.getPrincipal().getAttribute("preferred_username");
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));
        }

        throw new RuntimeException("Unexpected authentication type");
    }

    public void registerUser(String username, String email) {
        if (userRepository.existsByUsernameOrEmail(username, email)) {
            throw new UserAlreadyExistsException();
        } else {
            userRepository.save(new User(username, email));
        }
    }

}

