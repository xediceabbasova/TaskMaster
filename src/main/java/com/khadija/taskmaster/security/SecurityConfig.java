package com.khadija.taskmaster.security;

import com.khadija.taskmaster.exception.UserAlreadyExistsException;
import com.khadija.taskmaster.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private String issuerUri;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final JwtAuthConverter jwtAuthenticationConverter;
    private final UserService userService;

    public SecurityConfig(JwtAuthConverter jwtAuthenticationConverter, UserService userService) {
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
        this.userService = userService;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler((request, response, authentication) -> {
                            OAuth2User user = (OAuth2User) authentication.getPrincipal();
                            String username = user.getAttribute("preferred_username");
                            String email = user.getAttribute("email");
                            try {
                                userService.registerUser(username, email);
                                logger.info("User successfully registered in database: {}", username);
                            } catch (UserAlreadyExistsException e) {
                                logger.info("User already exists in database: {}", username);
                            }
                            response.sendRedirect("/dashboard");
                        }))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .build();
    }

}
