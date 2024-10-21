package com.khadija.taskmaster.service;

import com.khadija.taskmaster.exception.UserAlreadyExistsException;
import com.khadija.taskmaster.model.User;
import com.khadija.taskmaster.repository.UserRepository;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
            String username = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        throw new RuntimeException("No authenticated user found");
    }

    public void registerUser(String username, String email) {
        if (userRepository.existsByUsernameOrEmail(username, email)) {
            throw new UserAlreadyExistsException();
        } else {
            userRepository.save(new User(username, email));
        }
    }

}
