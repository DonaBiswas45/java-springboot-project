package com.example.EventService.kafka;

import java.time.Instant;

public class UserRegisteredEvent {
    private Long userId;
    private String email;
    private Instant registeredAt;

    public UserRegisteredEvent() {}
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public Instant getRegisteredAt() { return registeredAt; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setRegisteredAt(Instant registeredAt) { this.registeredAt = registeredAt; }
}