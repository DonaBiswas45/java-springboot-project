package com.example.EventService;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
	  @Autowired
	    private ApplicationEventPublisher publisher;

	    public void publishEventCreated(String eventName) {
	        EventCreatedEvent event = new EventCreatedEvent(this, eventName);
	        publisher.publishEvent(event);
	    }
}
