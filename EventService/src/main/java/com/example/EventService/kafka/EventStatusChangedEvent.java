package com.example.EventService.kafka;

import java.time.Instant;

public class EventStatusChangedEvent {
    private Long eventId;
    private String previousStatus;
    private String newStatus;
    private Instant changedAt;

    public EventStatusChangedEvent() {}
    public EventStatusChangedEvent(Long eventId, String previousStatus,
                                   String newStatus, Instant changedAt) {
        this.eventId = eventId;
        this.previousStatus = previousStatus;
        this.newStatus = newStatus;
        this.changedAt = changedAt;
    }
    public Long getEventId() { return eventId; }
    public String getPreviousStatus() { return previousStatus; }
    public String getNewStatus() { return newStatus; }
    public Instant getChangedAt() { return changedAt; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public void setPreviousStatus(String s) { this.previousStatus = s; }
    public void setNewStatus(String s) { this.newStatus = s; }
    public void setChangedAt(Instant t) { this.changedAt = t; }
}