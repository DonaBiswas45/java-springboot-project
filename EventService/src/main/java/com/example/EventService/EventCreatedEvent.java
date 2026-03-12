package com.example.EventService;

import org.springframework.context.ApplicationEvent;

public class EventCreatedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private final String eventName;

    public EventCreatedEvent(Object source, String eventName) {
        super(source);
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
