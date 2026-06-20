package com.example.EventService.kafka;

import java.time.Instant;

public class EventCreatedEvent {
    private Long eventId;
    private String eventName;
    private Instant createdAt;

    public EventCreatedEvent() {}

    public EventCreatedEvent(Long eventId, String eventName, Instant createdAt) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.createdAt = createdAt;
    }

    public Long getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public Instant getCreatedAt() { return createdAt; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
