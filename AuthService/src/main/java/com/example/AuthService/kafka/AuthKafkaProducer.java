package com.example.AuthService.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class AuthKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUserRegistered(Long userId, String email) {
        kafkaTemplate.send("user.registered",
            new UserRegisteredEvent(userId, email, Instant.now()));
    }
}
