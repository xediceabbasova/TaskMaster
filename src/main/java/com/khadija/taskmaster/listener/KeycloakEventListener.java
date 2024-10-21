package com.khadija.taskmaster.listener;

import com.khadija.taskmaster.service.UserService;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.springframework.stereotype.Component;

@Component
public class KeycloakEventListener implements EventListenerProvider {

    private final UserService userService;

    public KeycloakEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onEvent(Event event) {
        if (EventType.REGISTER.equals(event.getType())) {
            String username = event.getDetails().get("username");
            String email = event.getDetails().get("email");
            userService.registerUser(username, email);
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
    }

    @Override
    public void close() {
    }
}
