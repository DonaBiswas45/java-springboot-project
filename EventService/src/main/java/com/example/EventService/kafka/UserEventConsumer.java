package com.example.EventService.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(UserEventConsumer.class);

    @KafkaListener(topics = "user.registered", groupId = "event-service")
    public void onUserRegistered(UserRegisteredEvent event) {
        log.info("User registered — id: {}, email: {}", event.getUserId(), event.getEmail());
    }
}
