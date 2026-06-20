package com.example.EventService.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class EventKafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEventCreated(Long eventId, String eventName) {
        kafkaTemplate.send("event.created",
            new EventCreatedEvent(eventId, eventName, Instant.now()));
    }

    public void publishStatusChanged(Long eventId, String from, String to) {
        kafkaTemplate.send("event.status.changed",
            new EventStatusChangedEvent(eventId, from, to, Instant.now()));
    }
}